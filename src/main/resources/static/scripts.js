

function loginUser(event) {
      event.preventDefault();

      const form = document.getElementById('loginForm');
      const login = form.login.value;
      const password = form.password.value;

      const data = {
        login: login,
        password: password
      };

      // Make a POST request to /api/user/login
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
          // Replace '/profile' with your desired destination URL
          window.location.href = '/';
        } else {
          // Login failed, display the error message
          messageDiv.innerHTML = `Login failed: ${result.data.message}`;
        }
      })
      .catch(error => console.error('Error:', error));
    }