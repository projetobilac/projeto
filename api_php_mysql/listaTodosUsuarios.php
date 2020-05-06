<?php
//IMPORTANDO O SCRIPT DO BANCO DE DADOS
require_once('conexaoBD.php');

//CRIANDO A QUERY
$sql = "SELECT * FROM usuario";

//PEGANDO O RESULTADO
$r = mysql_query($con,$sql);

//CRIANDO UM VETOR EM BRANCO
$result = array();

//PERCORRENDO O VETOR
while($row = mysqli_fetch_array($r)){

    //INSERINDO O NOME E ID NO VETOR
    array_push($result,array(
        "id"=>$row['id'],
        "nome"=>$row['nome'],
        "email"=>$row['email'],
        "endereco"=>$row['endereco'],
        "telefone"=>$row['telefone'],
        "senha"=>$row['senha']
    ));
}

//EXIBINDO EM FORMATO JSON E FECHANDO CONEXÃO.
echo json_encode(array('result'=>$result));
mysqli_close($con)

?>