package br.com.pcsist.winthor.installer.components.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.izforge.izpack.api.data.InstallData;

/**
 * @author guilherme.pacheco
 */
public class OracleConnectionFactory implements ConnectionFactory {

  private static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";

  private String host;
  private String port;
  private String service;
  private String user;
  private String password;

  public OracleConnectionFactory(InstallData installData) {
    host = installData.getVariable("prod.dbhost");
    port = installData.getVariable("prod.dbport");
    service = installData.getVariable("prod.dbservice");
    user = installData.getVariable("prod.dbusername");
    password = installData.getVariable("prod.dbpassword");
  }

  @Override
  public Connection connection() throws ClassNotFoundException, SQLException {
    Class.forName(ORACLE_DRIVER);
    return DriverManager.getConnection(getUrl(), user, password);
  }

  @Override
  public String getUrl() {
    return String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, service);
  }

}
