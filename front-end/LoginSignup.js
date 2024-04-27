document.addEventListener('DOMContentLoaded', function () {
    function loginUser(event) {
      event.preventDefault(); 
      const username = document.getElementById('loginUsername').value;
      const password = document.getElementById('loginPassword').value;
      const xhr = new XMLHttpRequest();
      xhr.open('POST', '/LoginServlet', true); 
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) { 
          if (xhr.status === 200) { 
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
              console.log('Login successful. Redirecting...');
              window.location.href = '/dashboard'; // Redirect on successful login
            } else {
              console.error('Login failed:', response.message);
            }
          } else {
            console.error('Login error:', xhr.statusText);
          }
        }
      };
      xhr.send(`loginUsername=${encodeURIComponent(username)}&loginPassword=${encodeURIComponent(password)}`); // Send data
    }
    function signupUser(event) {
      event.preventDefault();
      const firstname = document.getElementById('firstname').value;
      const lastname = document.getElementById('lastname').value;
      const email = document.getElementById('email').value;
      const cemail = document.getElementById('cemail').value;
      const password = document.getElementById('password').value;
      const cpassword = document.getElementById('cpassword').value;
      if (email !== cemail) {
        console.error('Emails do not match.');
        return;
      }
      if (password !== cpassword) {
        console.error('Passwords do not match.');
        return;
      }
      const xhr = new XMLHttpRequest();
      xhr.open('POST', '/SignupServlet', true); 
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) { 
          if (xhr.status === 200) { 
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
              console.log('Signup successful. Account created.');
            } else {
              console.error('Signup failed:', response.message);
            }
          } else {
            console.error('Signup error:', xhr.statusText);
          }
        }
      };
      xhr.send(`firstname=${encodeURIComponent(firstname)}&lastname=${encodeURIComponent(lastname)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`); // Send data
    }
    const loginForm = document.querySelector('form[action="LoginServlet"]');
    const signupForm = document.querySelector('form[action="SignupServlet"]');
    loginForm.addEventListener('submit', loginUser); 
    signupForm.addEventListener('submit', signupUser); 
  });
  
