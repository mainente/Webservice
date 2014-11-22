package br.com.inventarioweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.inventarioweb.factory.ConnectionFactory;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.produto;

public class ProdutoDAO  extends ConnectionFactory {

	private static ProdutoDAO instance;
	
	

	public static ProdutoDAO getInstance(){
		if(instance == null)
			instance = new ProdutoDAO();
		return instance;
	}
	

	public produto AcharProduto(String id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		produto p = null;
		conexao = criarConexao();

		try {
			pstmt = conexao.prepareStatement("select produto._id,nome,dt_aquisicao,marca from produto,tipo_produto where tipo_produto._id=id_tipo and produto._id="+id);
			rs = pstmt.executeQuery();
			 p=new produto();
				while(rs.next()){
			p.setNome(rs.getString("nome"));
			p.setId(rs.getInt("_id"));
			p.setDt_aquisicao(rs.getString("dt_aquisicao"));
			p.setMarca(rs.getString("marca"));
				}
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return p;
	}
	public produto AcharProdutocod(String codigo_barra){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		produto p = null;
		conexao = criarConexao();

		try {
			pstmt = conexao.prepareStatement("select produto._id,nome,dt_aquisicao,marca,id_local from produto,tipo_produto where tipo_produto._id=id_tipo and codigo_barra="+codigo_barra);
			rs = pstmt.executeQuery();
			 p=new produto();
				while(rs.next()){
			p.setNome(rs.getString("nome"));
			p.setId(rs.getInt("_id"));
			p.setDt_aquisicao(rs.getString("dt_aquisicao"));
			p.setMarca(rs.getString("marca"));

				}
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return p;
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
