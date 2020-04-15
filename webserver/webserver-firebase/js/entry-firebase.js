const div = document.getElementById('show');

const db = firebase.database();
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
  div.appendChild(divPat);

  var patID = child2Snapshot.child("patientID").val();
  var divPatId = document.createElement('div');
  divPat.appendChild(divPatId);
  divPatId.innerHTML = "<b>PATIENT ID : #</b>" +  patID;

  var name = child2Snapshot.child("patientName").val().toUpperCase();
  var divName = document.createElement('div');
  divPat.appendChild(divName);
  divName.innerHTML = "<b>PATIENT NAME : </b>" +  name;

  var age = child2Snapshot.child("patientAge").val();
  var divAge = document.createElement('div');
  divPat.appendChild(divAge);
  divAge.innerHTML = "<b>PATIENT AGE : </b>" +  age;

  var disease = child2Snapshot.child("patientDisease").val().toUpperCase();
  var divDisease = document.createElement('div');
  divPat.appendChild(divDisease);
  divDisease.innerHTML = "<b>PATIENT DISEASE : </b>" +  disease;

  var bloodGroup = child2Snapshot.child("patientBloodGroup").val().toUpperCase();
  var divBloodGroup = document.createElement('div');
  divPat.appendChild(divBloodGroup);
  divBloodGroup.innerHTML = "<b>PATIENT BLOOD GROUP : </b>" +  bloodGroup;

  var divRoomNumber = document.createElement('div');
  divPat.appendChild(divRoomNumber);
  divRoomNumber.innerHTML = "<b>ROOM NUMBER : </b>" +  roomNumber;

  var divBedNumber = document.createElement('div');
  divPat.appendChild(divBedNumber);
  divBedNumber.innerHTML = "<b>BED NUMBER : </b>" +  bedNumber;

  });

});

// var key = snapshot.key;
// snapshot.forEach(function(childSnapshot) {
//     var patID = childSnapshot.key;
//     var divPat = document.createElement('div');
//     divPat.classList.add("patBox");
//     div.appendChild(divPat);
//
//
//     var divPatId = document.createElement('div');
//     divPat.appendChild(divPatId);
//     divPatId.innerHTML = "<b>PATIENT ID : #</b>" +  patID;
//
//     var name = childSnapshot.child("patientName").val().toUpperCase();
//     var divName = document.createElement('div');
//     divPat.appendChild(divName);
//     divName.innerHTML = "<b>PATIENT NAME : </b>" +  name;
//
//     var age = childSnapshot.child("patientAge").val();
//     var divAge = document.createElement('div');
//     divPat.appendChild(divAge);
//     divAge.innerHTML = "<b>PATIENT AGE : </b>" +  age;
//
//     var disease = childSnapshot.child("patientDisease").val().toUpperCase();
//     var divDisease = document.createElement('div');
//     divPat.appendChild(divDisease);
//     divDisease.innerHTML = "<b>PATIENT DISEASE : </b>" +  disease;
//
//     var bloodGroup = childSnapshot.child("patientBloodGroup").val().toUpperCase();
//     var divBloodGroup = document.createElement('div');
//     divPat.appendChild(divBloodGroup);
//     divBloodGroup.innerHTML = "<b>PATIENT BLOOD GROUP : </b>" +  bloodGroup;
//
//     var roomNumber = childSnapshot.child("roomNumber").val();
//     var divRoomNumber = document.createElement('div');
//     divPat.appendChild(divRoomNumber);
//     divRoomNumber.innerHTML = "<b>ROOM NUMBER : </b>" +  roomNumber;
//
//     var bedNumber = childSnapshot.child("bedNumber").val();
//     var divBedNumber = document.createElement('div');
//     divPat.appendChild(divBedNumber);
//     divBedNumber.innerHTML = "<b>BED NUMBER : </b>" +  bedNumber;
//
//   });]
});
