const db = firebase.database();
const statDiv = document.getElementById('status');
// dbRefObject.on('value', snap => console.log(snap.val()));

const submit = document.getElementById('submit');

submit.addEventListener("click", function(){
  var patID = Date.now() % Math.pow(10,7);
  console.log(patID);
  const name = document.getElementById('inputName').value;
  const age = document.getElementById('inputAge').value;
  // const disease = document.getElementById('inputDisease').value;
  const bloodGroup = document.getElementById('inputBloodGroup').value;
  const gender = document.getElementById('inputGender').value;
  const doctor = document.getElementById('inputDoctorName').value;
  const mobile = document.getElementById('inputNumber').value;
  const room = document.getElementById('inputRoomNumber').value;
  const bed = document.getElementById('inputBedNumber').value;
  const emergencyNumber = document.getElementById('inputEmergencyNumber').value;
  const address = document.getElementById('inputAddress').value;
  const medHistory = document.getElementById('inputMedHistory').value;
  const symptoms = document.getElementById('inputSymptoms').value;
  const insurance = document.getElementById('inputInsurance').value;
  const healthScheme = document.getElementById('inputHealthScheme').value;


  db.ref(patID).set({
    patientName: name,
    patientAge: age,
    // patientDisease: disease,
    patientBloodGroup: bloodGroup,
    patientGender: gender,
    consultingDoctor: doctor,
    patientMobile: mobile,
    roomNumber: room,
    bedNumber: bed,
    patientEmergencyNumber: emergencyNumber,
    patientAddress: address,
    patientMedHistory: medHistory,
    patientSymptoms: symptoms,
    patientInsurance: insurance,
    patientHealthScheme: healthScheme
}).then(function() {
      // statDiv.style.color='rgb(0,255,0)';
      statDiv.innerText = "Successfully added new patient!";
  }).catch(function(error) {
      var errorMessage = error.message;
      statDiv.style.color='rgb(255,0,0)';
      statDiv.innerText = errorMessage;
  });;

});
