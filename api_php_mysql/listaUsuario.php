<?php
//PEGANDO O ID
$id = $_GET['id'];

//IMPORTANDO BANCO DE DADOS
require_once('conexaoBD.php');

//CRIANDO SQL
$sql = "SELECT * FROM usuario WHERE id=$id";

//PEGANDO RESULTADO
$r = mysqli_query($con, $sql);

//INSERINDO O RESULTADO EM UM VETOR
//Obs: O método mysqli_fetch_array vai converter os valores 
//que estão vindo do banco em uma linha pra um vetor 
//que vai armazenar a variável row

//OBS: array_push -> Vai pegar os valores de cada linha (id, nome, cargo ,salario) e armazenar no array result

$result = array();
$row = mysqli_fetch_array($r);
array_push($result,array(
    "id"=>$row['id'],
    "nome"=>$row['nome'],
    "email"=>$row['email'],
    "endereco"=>$row['endereco'],
    "telefone"=>$row['telefone'],
    "senha"=>$row['senha']
));

//EXIBINDO EM FORMATO JSON
echo json_encode(array('result'=>$result));

//FECHA A CONEXÃO
mysqli_close($con);

?>
