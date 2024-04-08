<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Editor de Contato</title>
</head>
<body>
	<h1 class="text-primary">Editar Contato</h1>
	<div class="form-group">
		<form action="update" name="frmContato">
			<table>
				<tr>
					<td><input type="text" name="idcon" id="Caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="nome" class="Caixa1 form-control" value="<%out.print(request.getAttribute("nome"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="fone" class="Caixa2 form-control" value="<%out.print(request.getAttribute("fone"));%>"></td>
				</tr>
				<tr>
					<td><input type="email" name="email" class="Caixa1 form-control" value="<%out.print(request.getAttribute("email"));%>"></td>
				</tr>
			</table>
			<input type="submit" value="Salvar" class="btn btn-primary"
				onclick="validar()">
		</form>
	</div>
	<script type="text/javascript" src="script/validacao.js"></script>
</body>
</html>