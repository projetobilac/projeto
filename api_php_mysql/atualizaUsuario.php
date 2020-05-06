<?php
    if($_SERVER ['REQUEST_METHOD']=='POST'){
        //PEGANDO VALORES
        $id = $_POST['id'];
        $nome = $_POST ['nome'];
        $email = $_POST ['email'];
        $endereco = $_POST ['endereco'];
        $telefone = $_POST ['telefone'];
        $senha = $_POST ['senha'];

        //IMPORTANDO O SCRIPT DO BANCO DE DADOS
        require_once('conexaoBD.php');

        //CRIANDO A QUERY 
        $sql = "UPDATE usuario SET nome = '$nome', email = 
        '$email', endereco = '$endereco', telefone = '$telefone', senha = '$senha' WHERE id = $id";


        //ATUALIZANDO A TABELA DO BANCO DE DADOS 
        if(mysqli_query($con, $sql)){
            echo 'Usuário atualizado com sucesso';
        }else{
            echo 'Não foi possível atualizar o Usuario'
        }

        //FECHANDO A CONEXÃO COM O BANCO
        mysqli_close($con);
    }


?>