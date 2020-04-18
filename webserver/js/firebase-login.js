const auth = firebaseConfig1.auth();
const statDiv = document.getElementById('status');
auth.onAuthStateChanged(function(user) {
if (user) {
  window.location.href = 'postLogin/options.html';
  // history.forward(1);
}

else {
  const submit = document.getElementById('loginBt');

  submit.addEventListener("click", function(){
    const email = document.getElementById('emailID').value;
    const password = document.getElementById('pass').value;
    auth.signInWithEmailAndPassword(email, password).then(function() {
      statDiv.style.color='rgb(0,255,0)';
      statDiv.innerText = "Success";
      window.location.href = 'postLogin/options.html';
    }).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;
    console.log(errorMessage);
    statDiv.style.color='rgb(255,0,0)';
    statDiv.innerText = errorMessage;
    });

  });

}

});
