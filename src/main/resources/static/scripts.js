

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
          window.location.href = '/mainpage.html';
        } else {
          // Login failed, display the error message
          messageDiv.innerHTML = `Login failed: ${result.data.message}`;
        }
      })
      .catch(error => console.error('Error:', error));
    }

function registerUser(event) {
      event.preventDefault();

      const form = document.getElementById('RegisterForm');
    const email = form.email.value;
    const login = form.login.value;
    const password = form.password.value;
    const confirmpassword = form.confirmpassword.value;
    const country = form.country.value;
    const city = form.city.value;
    const postalcode = form.postalcode.value;
    const street = form.street.value;
    const address = form.address.value;

    if(password!==confirmpassword){
        const messageDiv = document.getElementById('registerMessage');
        messageDiv.innerHTML = `Hasła nie są  takie same!`;
        return;
    }

    const data = {
        email,
        login,
        password,
        country,
        city,
        postalcode,
        street,
        address
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
        const messageDiv = document.getElementById('registerMessage');
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


function checkUser() {
    fetch('/api/testlogin', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(result => {
            if (result.ok === true) {
                window.location.href = '/mainpage.html';
            }
        })
        .catch(error => console.error('Error:', error));
}


