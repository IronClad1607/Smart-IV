const auth = firebaseConfig1.auth();
const statDiv = document.getElementById('status');
const noEmail = "There is no user record corresponding to this identifier. The user may have been deleted."
const form = document.getElementById('formLogin');
auth.onAuthStateChanged(function(user) {
if (user) {
  window.location.href = 'postLogin/options.html';
  // history.forward(1);
}

else {
  const submit = document.getElementById('loginBt');

  submit.addEventListener("click", function(){
    statDiv.style.visibility = "hidden";
    const email = document.getElementById('emailID').value;
    const password = document.getElementById('pass').value;
    auth.signInWithEmailAndPassword(email, password).then(function() {
      statDiv.style.color='rgb(0,255,0)';
      statDiv.innerText = "Success";
      window.location.href = 'postLogin/options.html';
      statDiv.style.visibility = "visible";
    }).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;
    console.log(errorMessage);

    if(errorMessage == noEmail) {
      auth.createUserWithEmailAndPassword(email, password).then(function() {
        statDiv.style.color='rgb(0,255,0)';
        statDiv.innerText = "Created a new user";
        window.location.href = 'postLogin/options.html';
      }).catch(function(error) {
      // Handle Errors here.
      var errorCodeSignUp = error.code;
      var errorMessageSignUp = error.message;
      console.log(errorMessageSignUp);
      statDiv.style.color='rgb(255,0,0)';
      statDiv.innerText = errorMessageSignUp;
      statDiv.style.visibility = "visible";
    });
    }
    else {
      statDiv.style.color='rgb(255,0,0)';
      statDiv.innerText = errorMessage;
      statDiv.style.visibility = "visible";
    }
    });
    form.reset();
  });

}

});
