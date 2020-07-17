/**
 * 
 */
/**
 * @author JOE
 *
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConexaoBd {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static Connection getConexao() {
		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/galende", "root", "root");
			System.out.println("Conectado com sucesso!");
		} catch (final SQLException e) {
			System.err.println("Erro na conexao!");
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConexao(final Connection con) {
		try {
			if (con != null) {
				con.close();
				System.out.println("conexao encerrada");
			}

		} catch (final Exception e) {
			// TODO: handle exception
		}

	}

	public static void salvarLoginNoBD(final String loginUser) {

		final Connection con = ConexaoBd.getConexao();
		PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement("INSERT INTO logcalculadora (Usuario,Calculo,Tempo) Values(?,?,?)");
			stmt.setString(1, loginUser);
			stmt.setString(2, null);
			final Calendar data = Calendar.getInstance();

			final int hora = data.get(Calendar.HOUR_OF_DAY);
			final int min = data.get(Calendar.MINUTE);
			final int seg = data.get(Calendar.SECOND);
			final GregorianCalendar calendar = new GregorianCalendar();
			final int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			final int mes = calendar.get(GregorianCalendar.MONTH);
			final int ano = calendar.get(GregorianCalendar.YEAR);
			final String tempo = dia + "-" + mes + "-" + ano + " " + hora + ":" + min + ":" + seg;
			stmt.setString(3, tempo);
			stmt.executeUpdate();

		} catch (final Exception e) {
			// TODO: handle exception
		} finally {
			closeConexao(con);
		}
	}

	public static void salvarCalcNoBD(final String calculo) {

		final Connection con = ConexaoBd.getConexao();
		PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement("INSERT INTO logcalculadora (Usuario,Calculo,Tempo) Values(?,?,?)");
			stmt.setString(1, null);
			stmt.setString(2, calculo);
			final Calendar data = Calendar.getInstance();

			final int hora = data.get(Calendar.HOUR_OF_DAY);
			final int min = data.get(Calendar.MINUTE);
			final int seg = data.get(Calendar.SECOND);
			final GregorianCalendar calendar = new GregorianCalendar();
			final int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			final int mes = calendar.get(GregorianCalendar.MONTH);
			final int ano = calendar.get(GregorianCalendar.YEAR);
			final String tempo = dia + "-" + mes + "-" + ano + " " + hora + ":" + min + ":" + seg;
			stmt.setString(3, tempo);
			stmt.executeUpdate();

		} catch (final Exception e) {
			// TODO: handle exception
		} finally {
			closeConexao(con);
		}
	}

	public static Boolean autenticarUserBD(final String login, final String senha) throws SQLException {

		final Connection con = ConexaoBd.getConexao();

		final Statement st = con.createStatement();

		st.executeQuery("select * from UsuariosCalculadora ");
		final ResultSet rs = st.getResultSet();
		Boolean autenticado = false;

		while (rs.next()) {
			if ((rs.getString("usuario").equals(login)) && (rs.getString("senha").equals(senha))) {
				autenticado = true;
			}
		}

		closeConexao(con);
		return autenticado;

	}

	public static ArrayList consultarHistoricoBD() throws SQLException {

		final Connection con = ConexaoBd.getConexao();

		final Statement st = con.createStatement();

		st.executeQuery("select * from logcalculadora ");
		final ResultSet rs = st.getResultSet();

		ArrayList<String> historico = new ArrayList<>();

		String nome, calculo, tempo;

		while (rs.next()) {

			nome = rs.getString("usuario");
			calculo = rs.getString("calculo");
			tempo = rs.getString("tempo");
			historico.add("nome: " + nome + "Calculo: " + calculo + " Data: " + tempo);

		}
		closeConexao(con);
		return historico;

	}

}