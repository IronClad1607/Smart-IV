const div = document.getElementById('show');

const db = firebaseConfig2.database();
db.ref().on('value', function(snapshot) {
div.innerHTML = "";
snapshot.forEach(function(childSnapshot) {
  var roomNumber = parseInt(childSnapshot.key.slice(12),10);
  console.log(roomNumber);
  childSnapshot.forEach(function(child2Snapshot) {
    var bedNumber = parseInt(child2Snapshot.key.slice(11),10);
    console.log(bedNumber);

    var divPat = document.createElement('div');
    divPat.classList.add("patBox");
    divPat.classList.add("row");
    div.appendChild(divPat);

    var divPatCol1 = document.createElement('div');
    var divPatCol2 = document.createElement('div');

    divPat.appendChild(divPatCol1);
    divPat.appendChild(divPatCol2);

    divPatCol1.classList.add("col");

    divPatCol2.classList.add("col-md-2");
    divPatCol2.classList.add("dripClass");


    var divPatCol1Row1 = document.createElement('div');
    divPatCol1Row1.classList.add("row");
    divPatCol1Row1.classList.add("flex");
    var divPatCol1Row1Col1 = document.createElement('div');
    var divPatCol1Row1Col2 = document.createElement('div');
    var divPatCol1Row1Col3 = document.createElement('div');
    divPatCol1Row1Col1.classList.add("col-3");
    divPatCol1Row1Col2.classList.add("col-5");
    divPatCol1Row1Col3.classList.add("col");

    divPatCol1.appendChild(divPatCol1Row1);

    divPatCol1Row1.appendChild(divPatCol1Row1Col1);
    divPatCol1Row1.appendChild(divPatCol1Row1Col2);
    divPatCol1Row1.appendChild(divPatCol1Row1Col3);


    var patID = child2Snapshot.child("patientID").val();
    // var divPatId = document.createElement('div');
    // divPatCol1.appendChild(divPatId);
    divPatCol1Row1Col1.innerHTML = "<b>PATIENT ID : #</b>" +  patID;

    var name = child2Snapshot.child("patientName").val().toUpperCase();
    // var divName = document.createElement('div');
    // divPatCol1.appendChild(divName);
    divPatCol1Row1Col2.innerHTML = "<b>PATIENT NAME : </b>" +  name;

    var age = child2Snapshot.child("patientAge").val();
    // var divAge = document.createElement('div');
    // divPatCol1.appendChild(divAge);
    divPatCol1Row1Col3.innerHTML = "<b>PATIENT AGE : </b>" +  age;

    var divPatCol1Row3 = document.createElement('div');
    divPatCol1Row3.classList.add("row");
    divPatCol1Row3.classList.add("flex");
    var divPatCol1Row3Col1 = document.createElement('div');
    var divPatCol1Row3Col2 = document.createElement('div');
    var divPatCol1Row3Col3 = document.createElement('div');
    divPatCol1.appendChild(divPatCol1Row3);

    divPatCol1Row3.appendChild(divPatCol1Row3Col1);
    divPatCol1Row3.appendChild(divPatCol1Row3Col2);
    divPatCol1Row3Col1.classList.add("col");
    divPatCol1Row3Col2.classList.add("col");

    var disease = child2Snapshot.child("patientSymptoms").val().toUpperCase();
    // var divDisease = document.createElement('div');
    // divPatCol1.appendChild(divDisease);
    divPatCol1Row3Col1.innerHTML = "<b>PATIENT SYMPTOMS : </b>" +  disease;

    var bloodGroup = child2Snapshot.child("patientBloodGroup").val().toUpperCase();
    // var divBloodGroup = document.createElement('div');
    // divPatCol1.appendChild(divBloodGroup);
    divPatCol1Row3Col2.innerHTML = "<b>PATIENT BLOOD GROUP : </b>" +  bloodGroup;

    var divPatCol1Row2 = document.createElement('div');
    divPatCol1Row2.classList.add("row");
    divPatCol1Row2.classList.add("flex");
    var divPatCol1Row2Col1 = document.createElement('div');
    var divPatCol1Row2Col2 = document.createElement('div');
    var divPatCol1Row2Col3 = document.createElement('div');
    divPatCol1.appendChild(divPatCol1Row2);

    divPatCol1Row2.appendChild(divPatCol1Row2Col1);
    divPatCol1Row2.appendChild(divPatCol1Row2Col2);
    divPatCol1Row2.appendChild(divPatCol1Row2Col3);
    divPatCol1Row2Col1.classList.add("col-3");
    divPatCol1Row2Col2.classList.add("col-3");
    divPatCol1Row2Col3.classList.add("col");

    var ivFluid = child2Snapshot.child("patientIVFluid").val().toUpperCase();
    divPatCol1Row2Col3.innerHTML = "<b>PATIENT IV FLUID : </b>" +  ivFluid;

    // var divRoomNumber = document.createElement('div');
    // divPatCol1.appendChild(divRoomNumber);
    divPatCol1Row2Col1.innerHTML = "<b>ROOM NUMBER : </b>" +  roomNumber;

    // var divBedNumber = document.createElement('div');
    // divPatCol1.appendChild(divBedNumber);
    divPatCol1Row2Col2.innerHTML = "<b>BED NUMBER : </b>" +  bedNumber;

    // var divDripStatus = document.createElement('div');
    var dripStatus = child2Snapshot.child("dripStatus").val();
    if(dripStatus == true) {
      divPatCol2.innerHTML = '<img class="dripImage" src="../img/red.png">';
    }
    else
    divPatCol2.innerHTML = '<img class="dripImage" src="../img/green.png">';
    // divPatCol2.appendChild(divDripStatus);
    });

  });

});
