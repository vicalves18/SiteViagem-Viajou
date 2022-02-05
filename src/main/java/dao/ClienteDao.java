package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MySqlConnection;
import model.Cliente;

public class ClienteDao implements CRUD{
	
	private static Connection connection = MySqlConnection.createConnection();
	private static String sql;
	
	public static void create(Cliente cliente) {
		sql = "INSERT INTO clientes VALUES(null, ?, ?, ?, ?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getNascimento());
			preparedStatement.setString(4, cliente.getSituacao());
			preparedStatement.setString(5, cliente.getDestino());
			
			preparedStatement.executeUpdate();
			
			System.out.println("---Informações inseridas com Sucesso---");
			
		}catch(SQLException e){
			System.out.println("---Erro ao inserir informações---"+e.getMessage());
		}
	}
	public static void delete(int clienteId) {
		sql = "DELETE FROM clientes WHERE id=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clienteId);
			preparedStatement.executeUpdate();
			System.out.println("---Informações de Cliente deletadas---");
			
		}catch(SQLException e) {
			System.out.println("---Erro ao deletar informações do Cliente"+e.getMessage());
		}
		
	}
	public static List<Cliente> find(String pesquisa){
		sql = String.format("SELECT * FROM clientes WHERE nome LIKE '%s%%' OR cpf LIKE '%s%%'", pesquisa, pesquisa);
		List<Cliente> clientes=new ArrayList<Cliente>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setNascimento(resultSet.getString("nascimento"));
				cliente.setSituacao(resultSet.getString("situacao"));
				cliente.setDestino(resultSet.getString("destino"));
				
				clientes.add(cliente);
			}
			System.out.println("---Foram encontrados Clientes---");
			return clientes;
	
			
		}catch(SQLException e) {
			System.out.println("--Não foram encontrados Clientes"+ e.getMessage());
			
			return null;
			
		}

	}
	public static Cliente findByPK(int clienteId) {
		
		sql = String.format("SELECT * FROM clientes WHERE id=%d", clienteId);
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Cliente cliente = new Cliente();
			
			while(resultSet.next()) {
				
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setNascimento(resultSet.getString("nascimento"));
				cliente.setSituacao(resultSet.getString("situacao"));
				cliente.setDestino(resultSet.getString("destino"));
				
			}
			System.out.println("---Cliente Atualizado---");
			return cliente;
	
			
		}catch(SQLException e) {
			System.out.println("--Não foi possível atualizar Cliente"+ e.getMessage());
			
			return null;
			
		}
		
	}
	public static void update(Cliente cliente) {
		sql = "UPDATE clientes SET nome=?,cpf=?, nascimento=?, situacao=?, destino=? WHERE id=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getNascimento());
			preparedStatement.setString(4, cliente.getSituacao());
			preparedStatement.setString(5, cliente.getDestino());
			preparedStatement.setInt(6, cliente.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("---Informações atualizadas com sucesso---");
			
		}catch(SQLException e){
			System.out.println("---Erro ao atualizar informações---"+e.getMessage());
		}
	}
}
