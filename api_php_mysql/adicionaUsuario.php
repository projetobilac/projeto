<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    //PEGANDO VALORES
    $nome = $_POST ['nome'];
    $email = $_POST ['email'];
    $endereco = $_POST ['endereco'];
    $telefone = $_POST ['telefone'];
    $senha = $_POST ['senha'];

    //CRIANDO O MÉTODO SQL
    $sql = "INSERT INTO usuario (nome, email, endereco, telefone, senha)
    VALUES ('$nome', '$email', '$endereco', 'telefone', 'senha')";

    //IMPORTANDO O SCRIPT DO BANCO
    require_once ('conexaoBD.php');


    if(mysqli_query($con,$sql)){
        echo 'Funcionário Cadastrado com sucesso'
    }else{
        echo 'Não foi possível cadastrar o funcionário'
    }

    //FECHANDO O BANCO DE DADOS
    mysqli_close($con);

}

?>