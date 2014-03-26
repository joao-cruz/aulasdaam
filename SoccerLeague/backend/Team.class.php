<?php
/**
* 
*/
class Team
{
	private $dbconn;
    protected $tname;
    protected $tcity;
    protected $tfy;
	
	function __construct()
	{
		$this->dbconn = mysql_connect('localhost', 'allofads_cjcs', '.com0673');
        if(!$this->dbconn) {
            error_log('problems on connection');
            return;
        }
        mysql_select_db('allofads_aulas', $this->dbconn);
	}

    public function addTeam($name, $city, $fy)
    {
        $this->tname = $name;
        $this->tcity = $city;
        $this->tfy = $fy;
    }

    public function save()
    {
        $sql = "insert into team (tname, tcity, tfy) values ('".$this->tname."', '".$this->tcity."', '".$this->tfy."')";
        error_log("insert into team (tname, tcity, tfy) values ('".$this->tname."', '".$this->tcity."', '".$this->tfy."')");
        mysql_query($sql, $this->dbconn);
    }

    public function delete($teamid)
    {
        $sql = "delete from team where id = ".$teamid;
        $rs = mysql_query($sql, $this->dbconn);
        if($rs == true) return true;
        else return false;

    }

    public function load()
    {

    }

    public function getAll()
    {
        $sql = "select * from team";
        $rs = mysql_query($sql, $this->dbconn);

        $teams = array();

        $n=0;
        while(($a = mysql_fetch_row($rs))) {
            $teams[$n] = array(
                "id" => $a[0],
                "tname" => $a[1],
                "tcity" => $a[2],
                "tfy" => $a[3]
            ); 
            $n++;
        }

        return $teams;
    }

    function __destruct()
    {
        mysql_close($this->dbconn);
    }
}
?>