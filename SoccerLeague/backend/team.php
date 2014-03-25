<?php
include_once('Team.class.php');
if($_REQUEST['op']=='ADDTEAM') {
    error_log('in add team');
    error_log($_REQUEST['name'].'-'.$_REQUEST['city'].'-'.$_REQUEST['tfy']);
    $team = new Team();
    $team->addTeam($_REQUEST['name'], $_REQUEST['city'], $_REQUEST['tfy']);
    $team->save();

    $result = array(
        "result" => "OK",
        "status" => "Team added with success!"
    );

    echo json_encode($result);
}

if($_REQUEST['op']=='LIST') {
    $team = new Team();
    $teams = $team->getAll();

    $result = array(
        "result" => "OK",
        "teams" => $teams
    );

    echo json_encode($result);
}
?>