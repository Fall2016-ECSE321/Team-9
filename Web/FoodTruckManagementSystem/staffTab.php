
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Food Truck Management System</title>

<style>
  .btn-default {
    background: #000;
    color: #fff;
  }
  
  .btn-default:hover {
    background: #fff;
    color: #000;
  }
  .error {
      color: #D8000C;
  }
  .success {
      color: #4F8A10;
  }
  .positon_time {
    position: relative;
    left: 60px;
  }
</style>
</head>
<body >

<div class="container">
  <h3 ><strong style="color:#808080">Food Truck Management System</strong><img style="width: 10%;
    height: auto;" src="img/logo.png"></h3>
  <ul class="nav nav-tabs">
    <li><a href="index.php">Home</a></li>
    <li><a href="inventoryTab.php">Inventory</a></li>
    <li class="active"><a href="staffTab.php">Staff</a></li>
    <li><a href="reportTab.php">Report</a></li>
  </ul>
    <br>

    <?php
    require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
    require_once 'model/StaffMember.php';
    require_once 'model/Manager.php';

    session_start();
    // Retreive the data from the model
    $pm = new PersistenceFoodTruckManagementSystem ();
    $m = $pm->loadDataFromStore ();

    ?>
    <br>

    <h4 style="color:#778899"> <strong>Add New Staff</strong></h4>
    <div style="background-color:#BCB7C1;color:black;padding:20px;"">
            <!-- <h4> <strong>Equipment Item</strong></h4> -->
       
      <form class="form-inline" action="addStaffMember.php" method="post">
        
        <span class="error input-sm">
          <?php
            if (isset ( $_SESSION ['errorStaff'] ) && ! empty ( $_SESSION ['errorStaff'] )) {
              echo " * " . $_SESSION ["errorStaff"];
              session_unset($_SESSION ["errorStaff"]);
            }
          ?>
        <span class="success input-md">
          <?php
            
            if (isset ( $_SESSION ['successStaff'] ) && ! empty ( $_SESSION ['successStaff'] )) {
            echo $_SESSION ["successStaff"];
            session_unset($_SESSION ["successStaff"]);
            } 
          ?>
        </span> 
        </span>
        <br>
        <div class="form-group">
          &nbsp&nbsp <input class="form-control input-sm" type="text"
            name="staff_name" placeholder="Enter First Last Name" />
        </div>
        &nbsp&nbsp
        <div class="form-group">
        <label for="select_1">Select Role:</label>
          <select class="form-control" id="select_1" name="staff_role">
              <option value="cashier">Cashier</option>
              <option value="chef">Chef</option>
              <option value="manager">Manager</option>
              <option value="clerk">Inventory Clerk</option>
          </select>
        </div>
        <br><br>&nbsp&nbsp
        <button type="submit" name="addStaffMember" class="btn btn-default">Add</button>
      </form>
    </div>
    <br>
    <h4 style="color:#778899"><strong>Register Staff's Weekly Schedule</strong></h4>
    <div style="max-height: 450px;overflow-y: scroll;; background-color:#BCB7C1;color:black;padding:20px;"">
            <!-- <h4> <strong>Equipment Item</strong></h4> -->
       
      <form class="form-inline" action="addSchedule.php" method="post">
        
        <span class="error input-sm">
          <?php
            if (isset ( $_SESSION ['errorSchedule'] ) && ! empty ( $_SESSION ['errorSchedule'] )) {
              echo " * " . $_SESSION ["errorSchedule"];
              session_unset($_SESSION ["errorSchedule"]);
            }
          ?>
        <span class="success input-md">
          <?php
            
            if (isset ( $_SESSION ['successSchedule'] ) && ! empty ( $_SESSION ['successSchedule'] )) {
            echo $_SESSION ["successSchedule"];
            session_unset($_SESSION ["successSchedule"]);
            } 
          ?>
        </span> 
        </span>
        <br><br>
      
    
        <div class="form-group">
        <!-- Change equipment to staff name later on, this is just a place holder fo -->
        <?php
          echo "<p><strong>Select Staff Name: </strong><select class='form-control input-md' name='staffMemberSpinner'>";
          foreach ($m->getStaffMembers() as $staff) {
            echo "<option>" . $staff->getName() ."</option>";
          }
          echo "</select>";
        ?>
        <button align="right" type="submit" name="removeStaff" class="btn btn-default">Remove Staff</button>
        
        <br>
        <dd>

          <label style="position: relative;left: 100px;">Start Time</label>

          <label style="position: relative;left: 150px;">End Time </label>
      
        </dd>
       
        <label>Monday:</label>
          <input style="position: relative;left: 24px;" class="form-control input-sm" type="time"
            name="start_time1" />
          <input style="position: relative;left: 34px;" class="form-control input-sm" type="time"
          name="end_time1"  />
      
        <br><br>
        <label>Tuesday:</label>
          <input style="position: relative;left: 22px;" class="form-control input-sm" type="time"
            name="start_time2" />
          <input style="position: relative;left: 32px;" class="form-control input-sm" type="time"
          name="end_time2"  />

        <br><br>
         <label>Wednesday:</label>
          <input class="form-control input-sm" type="time"
            name="start_time3" />
         <input style="position: relative;left: 10px;" class="form-control input-sm" type="time"
          name="end_time3" />

        <br><br>
         <label>Thursday:</label>
          <input style="position: relative;left: 16px;" class="form-control input-sm" type="time"
            name="start_time4" />
          <input style="position: relative;left: 26px;" class="form-control input-sm" type="time"
          name="end_time4"  />

        <br><br>
        <label>Friday:</label>
          <input style="position: relative;left: 36px;" class="form-control input-sm" type="time" name="start_time5" />
          <input style="position: relative;left: 46px;" class="form-control input-sm" type="time"
          name="end_time5" />

        <br><br>
         <label>Saturday:</label>
          <input style="position: relative;left: 18px;"" class="form-control input-sm" type="time"
            name="start_time6"  />
          <input style="position: relative;left: 28px;" class="form-control input-sm" type="time" name="end_time6"/>

        <br><br>
         <label>Sunday:      </label>
          <input style="position: relative;left: 28px;" class="form-control input-sm" type="time"
            name="start_time7" />
          <input style="position: relative;left: 38px;" class="form-control input-sm" type="time"
          name="end_time7"  />
        <br><br>
        <!-- <input type="button"  class="btn btn-default" onclick="myFunction()" value="Cancel"> -->
        <button type="submit" name="editStaffSchedule" class="btn btn-default">Save</button>
        </div>
      </form>

  </div>

</div>    

</body>
</html>