

function loginUser(event) {
      event.preventDefault();

      const form = document.getElementById('loginForm');
      const login = form.login.value;
      const password = form.password.value;

      const data = {
        login: login,
        password: password
      };

      fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(response => response.json())
      .then(result => {
        const messageDiv = document.getElementById('loginMessage');
        if (result.ok === true) {
          // Login successful
          messageDiv.innerHTML = 'Login successful.';
          // Redirect to the user's profile or home page
          window.location.href = '/';
        } else {
          // Login failed, display the error message
          messageDiv.innerHTML = `Login failed: ${result.data.message}`;
        }
      })
      .catch(error => console.error('Error:', error));
    }

function registerUser(event) {
      event.preventDefault();

      const form = document.getElementById('registrationForm');
      const username = form.username.value;
      const email = form.email.value;
      const password = form.password.value;

      const data = {
        login: username,
        email: email,
        password: password
      };

      // Make a POST request to /api/user/register
      fetch('/api/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(response => response.json())
      .then(result => {
        const messageDiv = document.getElementById('registrationMessage');
        if (result.ok === true) {
          // Registration successful
          messageDiv.innerHTML = 'Registration successful.';
          window.location.href = '/login';
        } else {
          // Registration failed, display the error message
          messageDiv.innerHTML = `Registration failed: ${result.data.message}`;
        }
      })
      .catch(error => console.error('Error:', error));
    }