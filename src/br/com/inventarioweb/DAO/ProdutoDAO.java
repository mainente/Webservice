package br.com.inventarioweb.DAO;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import br.com.inventarioweb.factory.ConnectionFactory;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.produto;
import br.com.inventarioweb.model.foto;

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
			p.setId_local(rs.getInt("id_local"));

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
	public foto foto(String id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		foto f = null;
		conexao = criarConexao();

		try {
			pstmt = conexao.prepareStatement("select foto._id,formato from produto,foto where produto_id=produto._id and produto._id="+id+ " order by _id desc");
			rs = pstmt.executeQuery();
			rs.next();
			
			f=new foto();
			
			f.setId(rs.getInt("_id"));
			f.setFormato(rs.getString("formato"));
			
		
		} catch (Exception e) {
			System.out.println("foto não encontrada: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		
		return f;
	}
	@SuppressWarnings("resource")
	public byte[] imagem(foto f){
		byte[] arquivo = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		foto foto = null;
		conexao = criarConexao();
		Connection conexao2 = null;
		//PreparedStatement pstmt = null;
			conexao2 = criarConexao();
		
		try {
			pstmt = conexao2.prepareStatement("insert into foto(produto_id,data_f,formato) values("+"'"+f.getId()+"'"+","+"'"+f.getData_f()+"'"+",'"+f.getFormato()+"'"+")");

			pstmt.executeUpdate();

				pstmt2 = conexao.prepareStatement("select foto._id,formato from produto,foto where produto_id=produto._id and produto._id="+f.getId()+ " order by _id desc");
				rs = pstmt2.executeQuery();
				rs.next();
				
				foto=new foto();
				
				foto.setId(rs.getInt("_id"));
				foto.setFormato(rs.getString("formato"));
				
			
			
			arquivo=Base64.decode(f.getImagem());
			FileOutputStream fos;
			fos=new FileOutputStream("/home/mainente/imagens/"+f.getId()+"."+foto.getId()+foto.getFormato());
			fos.write(arquivo);
			FileDescriptor fd=fos.getFD();
			fos.flush();
			fd.sync();
			fos.close();
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}catch (FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	} catch (Exception e) {
		System.out.println("foto não encontrada: " + e);
		e.printStackTrace();
	} finally {
		fecharConexao(conexao, pstmt, rs);
	}
		return arquivo;

	}
}
