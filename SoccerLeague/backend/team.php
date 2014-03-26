<?php
include_once('Team.class.php');
if($_REQUEST['op']=='ADDTEAM') {
    error_log('in add team');
    error_log($_REQUEST['name'].'-'.$_REQUEST['city'].'-'.$_REQUEST['tfy']);

    if(!isset($_REQUEST['name']) || $_REQUEST['name']=='') {
        $result = array(
        "result" => "ERR",
        "status" => "Team name cannot be empty!!!"
        );
    } else {
        $team = new Team();
        $team->addTeam($_REQUEST['name'], $_REQUEST['city'], $_REQUEST['tfy']);
        $team->save();

        $result = array(
            "result" => "OK",
            "status" => "Team added with success!"
        );
    }
    echo json_encode($result);
    
} elseif($_REQUEST['op']=='LISTTEAMS') {
    error_log('in list teams');
    $team = new Team();
    $teams = $team->getAll();

    $result = array(
        "result" => "OK",
        "teams" => $teams
    );

    echo json_encode($result);
    
} elseif($_REQUEST['op']=='REMOVETEAM') {
    error_log('in remove teams');
    $team = new Team();
    if($team->delete($_REQUEST['id'] == true)) {
        $result = array(
            "result" => "OK",
            "status" => "Deleted with success!"
        );
    } else {
        $result = array(
            "result" => "ERR",
            "status" => "Problems with the deletion!"
        );
    }
    echo json_encode($result);
}
?>