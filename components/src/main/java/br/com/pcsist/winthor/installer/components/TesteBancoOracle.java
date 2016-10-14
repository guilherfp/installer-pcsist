package br.com.pcsist.winthor.installer.components;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.handler.Prompt;
import com.izforge.izpack.panels.userinput.action.ButtonAction;
import com.izforge.izpack.util.Console;

import br.com.pcsist.winthor.installer.components.connection.ConnectionFactory;
import br.com.pcsist.winthor.installer.components.connection.OracleConnectionFactory;
import br.com.pcsist.winthor.installer.components.shared.Constantes;

/**
 * @author guilherme.pacheco
 */
public class TesteBancoOracle extends ButtonAction {

  private final String SQL_TEST = "select 1 from dual";
  private ConnectionFactory connectionFactory;

  public TesteBancoOracle(InstallData installData) {
    super(installData);
    connectionFactory = new OracleConnectionFactory(installData);
  }

  @Override
  public boolean execute() {
    try {
      return testConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public boolean testConnection() throws SQLException, ClassNotFoundException {
    try (Connection connection = connectionFactory.connection()) {
      try (Statement statement = connection.createStatement()) {
        statement.executeQuery(SQL_TEST).close();
        installData.setVariable(Constantes.PROD_URL, connectionFactory.getUrl());
        return true;
      }
    }
  }

  @Override
  public boolean execute(Console console) {
    if (!execute()) {
      console.print("ERRO!");
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean execute(Prompt prompt) {
    if (!execute()) {
      prompt.warn("ERRO!");
      return false;
    } else {
      return true;
    }
  }

}
