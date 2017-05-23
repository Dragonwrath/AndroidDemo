

<?php if(empty($_REQUEST['state'])) {
            echo "No state sent";
        } else {
            $search = trim($_REQUEST['state']);
            switch($search){
                case "MO" :
                    $result = "<ul><li>St.louis</li><br/><li>Kansas City</li></ul>";
                    break;
                default:
                    $result = "No City Found";
                 break;
            }
            echo "<h3>Cities:</h3><p>" . $result . "</p>";
        }
?>
