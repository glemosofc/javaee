package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Módulo de Conexão
	// Parâmetros para Conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "backend@2024";

	// Método de Conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD -> CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome,fone,email) values (?,?,?)";

		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a consulta(query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query (Ctrl + Enter)
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	// CRUDE - READ
	public ArrayList<JavaBeans> listarContatos() {

		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "SELECT * from contatos order by idcon";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do Banco
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));

			}
			con.close();
			return contatos;

		} catch (Exception e) {

		}
		return null;
	}
	
	/*
	 * Teste de Conexão public void testeConexao() { try { Connection con =
	 * conectar(); System.out.println(con); con.close(); }catch (Exception e) {
	 * System.out.println(e); }
	 */

	// CRUD UPDATE
	// Selecionar contato
	public void selecaoContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				// Setar as variáveis JavaBeans
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void editarContato(JavaBeans contato) {
		String atualizar = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(atualizar);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);	
		}	
	}
	
	public void deletarContato(JavaBeans contato) {
		String deletar = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(deletar);
			pst.setInt(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);	
			}	
	}
	
	public void gerarRelatorio(JavaBeans contato) {
		String report = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(report);
			pst.setInt(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);	
			}	
	}
}