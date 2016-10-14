package br.com.pcsist.winthor.installer.components.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author guilherme.pacheco
 */
public interface ConnectionFactory {

  Connection connection() throws ClassNotFoundException, SQLException;

  String getUrl();

}
