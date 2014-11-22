package br.com.inventarioweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.inventarioweb.factory.ConnectionFactory;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.local;



public class localDAO extends ConnectionFactory {

	private static localDAO instance;
	
	

	public static localDAO getInstance(){
		if(instance == null)
			instance = new localDAO();
		return instance;
	}
	

	public ArrayList<local> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		conexao = criarConexao();
		local = new ArrayList<local>();
		try {
			pstmt = conexao.prepareStatement("select * from local order by nome");
			rs = pstmt.executeQuery();
			local loc ;

			while(rs.next()){
				 loc = new local();
				loc.setNome(rs.getString("nome"));
				loc.setId(rs.getInt("_id"));
				loc.setLocalizacao(rs.getString("localizacao"));
				pstmt2 = conexao.prepareStatement("select estado from inventario where local_id="+ rs.getInt("_id")+ " order by dt_criacao ");
				rs2 = pstmt2.executeQuery();
				int quant=0;
//				rs2.first();
				loc.setStatus("-");

				while(rs2.next()){
					loc.setStatus(rs2.getString("estado"));

					quant++;
				}
				loc.setQuant(quant);
				local.add(loc);
			}
			 loc = new local();

			loc.setId(0);;
			loc.setNome("");
			loc.setLocalizacao("");
			loc.setStatus("");
			local.add(loc);
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return local;
	}
	public  void deletar(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> clientes = null;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("Delete from cliente where id="+id);
			rs = pstmt.executeQuery();
		
					
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}


}
