var produkty = [];
var nosniki = [];
var transactions = [];

function addToCart(id) {
	var gameContainer = document.querySelector('.game-container[data-product-id="' + id + '"]');

	if (gameContainer) {
		var button = gameContainer.querySelector('button');

		if (button) {
			if (button.dataset.added == 'false') {
				button.dataset.added = 'true';
				button.textContent = 'Dodano';
				zapiszIdWCiasteczkach(id);
			}
			else {
				button.dataset.added = 'false';
				button.textContent = 'Dodaj do koszyka';
				usunIdZCiasteczek(id);
			}
		}
	}
}

function zapiszIdWCiasteczkach(idProduktu) {
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');

		if (!zapisaneId) {
			zapisaneId = [];
		} else {
			zapisaneId = JSON.parse(zapisaneId);
		}

		if (!zapisaneId.includes(idProduktu)) {
			zapisaneId.push(idProduktu);

			localStorage.setItem('zapisaneId', JSON.stringify(zapisaneId));

			console.log('Id produktu ' + idProduktu + ' zostało zapisane w ciasteczkach.');
		} else {
			console.log('Id produktu ' + idProduktu + ' już istnieje w ciasteczkach.');
		}
	} else {
		console.log('Twoja przeglądarka nie obsługuje ciasteczek.');
	}
}

function usunIdZCiasteczek(idProduktu) {
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');

		if (zapisaneId) {
			zapisaneId = JSON.parse(zapisaneId);

			var index = zapisaneId.indexOf(idProduktu);

			if (index !== -1) {
				zapisaneId.splice(index, 1);

				localStorage.setItem('zapisaneId', JSON.stringify(zapisaneId));

				console.log('Id produktu ' + idProduktu + ' zostało usunięte z ciasteczek.');
			} else {
				console.log('Id produktu ' + idProduktu + ' nie istnieje w ciasteczkach.');
			}
		} else {
			console.log('Brak zapisanych id w ciasteczkach.');
		}
	} else {
		console.log('Twoja przeglądarka nie obsługuje ciasteczek.');
	}
}

/*

		fetchData("/api/carrier", data => {
			nosniki.push(data);
			console.log(data);
		});
*/

function onLoad(strona) {
	if (strona == null) {
		produkty.push({ id: 1, title: "GTA VI", seller: "BigSmoke125kg", price: 300.33, image: "img/gta.png" });
		produkty.push({ id: 2, title: "Half-Life 3", seller: "GabeNewell57", price: 159.99, image: "img/halflive3.png" });
		produkty.push({ id: 3, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: 269.99, image: "img/minecraft.png" });
		produkty.push({ id: 4, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: 269.99, image: "img/minecraft.png" });
		produkty.push({ id: 5, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: 269.99, image: "img/minecraft.png" });
		transactions.push({ id: 4, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: -269.99, image: "img/minecraft.png" });
		transactions.push({ id: 5, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: 269.99, image: "img/minecraft.png" });
		transactions.push({ id: 6, title: "Minecraft 2", seller: "DefinitelyNotNotch", price: 269.99, image: "img/minecraft.png" });
	}
	if (strona == 1) {
		console.log("Koszyk");
		try {
			showItemsInCart();
		}
		catch (e) {
			console.log(e);
		}
		fetchData("/api/products", data => {
			produkty = data.productList;
			console.log(data);
			showItemsInCart();
		});
	}
	else if (strona == 2) {//mainpage
		console.log("else");
		try {
			showProducts();
		}
		catch (e) {
			console.log(e);
		}
		fetchData("/api/products", data => {
			produkty = data.productList;
			console.log(data);
			showProducts();
		});
		if (typeof Storage !== 'undefined')
		{
			var doSzukania = localStorage.getItem('doSzukania');
			if(doSzukania)
			{
				document.getElementById("szukaj_input").value = doSzukania;
				filterProducts();
				localStorage.removeItem('doSzukania');
			}
		}
	}
	else if (strona == 3) {//
		fetchData("api/transactions", data => {
			transactions = data.productList;
			console.log(data);
			showTransactions();
		});
		try {
			showTransactions();
		}
		catch (e) {
			console.log(e);
		}
	}
	else if (strona == 4) {//index
		fetchData("/api/products", data => {
			produkty = data.productList;
			console.log(data);
			showProducts();
		});
		checkUser().then(x => {
			if (typeof Storage !== 'undefined') {
				var doSzukania = localStorage.getItem('doSzukania');
				if (doSzukania) {
					document.getElementById("szukaj_input").value = doSzukania;
					filterProducts();
					localStorage.removeItem('doSzukania');
				}
			}
		});
	}

}


function zaladuj() {
	const form = document.getElementById("container");
	const data = {
		"country": form.country.value,
		"city": form.city.value,
		"zipCode": form.postalcode.value,
		"street": form.street.value,
		"number": form.address.value
	};
	fetchData("/api/profile", resp => {
		form.country.value = resp.country;
		form.city.value = resp.city;
		form.postalcode.value = resp.zipCode;
		form.street.value = resp.street;
		form.address.value = resp.number;
    
	});
}

function zmien(event) {
	event.preventDefault();
	const form = document.getElementById("container");
	const data = {
		"country": form.country.value,
		"city": form.city.value,
		"zipCode": form.postalcode.value,
		"street": form.street.value,
		"number": form.address.value
	};
	fetchPost("/api/profile", data, resp => {
		const mess = document.getElementById("wiadomosc");
		if (resp.ok === true) {
			mess.innerHTML = "Pomyślnie zmieniono dane";
			
		}
		else {
			mess.innerHTML = "Nie zmieniono danych";
		}

	});
}

function wystaw(event) {
	event.preventDefault();
	const form = document.getElementById("container");
	const data = {
		"title": form.title.value,
		"price": parseFloat(form.price.value),
		"stock": parseInt(form.stock.value),
		"carrier": form.carrier.value,
		"link": form.link.value
	};
	fetchPost("/api/products", data, resp => {
		const mess = document.getElementById("wiadomosc");
		if (resp.ok === true) {
			mess.innerHTML = "Pomyślnie dodano";
			
		}
		else {
			mess.innerHTML = "Nie dodano";
		}

	});
}

function kup() {
	console.log("klik");
	var zapisane = [];
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');
		if (zapisaneId) {
			zapisane = (JSON.parse(zapisaneId));
		}
	}
	var data = {
		productList:[]
	};

	zapisane.forEach(t => {
		data.productList.push({ id: t });
	});
	console.log(data);

	fetchPost("/api/buy", data, resp => {
		localStorage.setItem('zapisaneId', JSON.stringify([]));
		window.location.href = "/produkty.html";
	});
}

function redirect()
{
	var input = document.getElementById("szukaj_input");
	var filter = input.value;
	localStorage.setItem('doSzukania', filter);
	window.location.href = 'index.html';
}

function showTransactions() {

	var containter = document.getElementById("transaction-items");
	containter.innerHTML = "";
	if (transactions.length == 0) {
		containter.innerHTML += '<h1 style="margin-left: 10%;">Brak transakcji</h1>';
		// tu mozna guzik wyłączyć   <- prawda ~ Mateusz
	}
	else {
		var sum = 0;
		transactions.forEach(transactions => {
			var code = "";
			code += '<div class="product-tile" data-product-id="' + transactions.id + '">';
			code += '<img src="' + transactions.image + '" alt="' + transactions.title + '" class="product-logo">';
			code += '<div class="product-details">';
			code += '<h3>' + transactions.title + '</h3>';
			if (transactions.price <= 0) code += '<p style="color:red;">'
			else code += '<p style="color:green;">';
			code += transactions.price + ' zł</p>';
			code += '</div>';
			code += '</div>';
			console.log(code);
			containter.innerHTML += code;

			sum += transactions.price;
		});
		var code = "";
		code += '<div class="product-tile">';
		code += '<div class="product-details" >';
		code += '<p>Saldo:</p>';
		if (sum <= 0) code += '<p style="color:red;">'
		else code += '<p style="color:green;">';
		code += sum + ' zł</p>';
		code += '</div>';
		code += '</div>';
		console.log(code);
		containter.innerHTML += code;
	}

}

function filterProducts() {
	var input = document.getElementById("szukaj_input");
	var filter = input.value;
	var containter = document.getElementById("container");
	containter.innerHTML = '<div class="row" id="row0"></div>';
	var row = 0;
	var rowcontainer = document.getElementById("row" + row);
	var i = 0;
	var zapisane = [];
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');
		if (zapisaneId) {
			zapisane = (JSON.parse(zapisaneId));
		}
	}
	console.log(zapisane);
	produkty.forEach(produkt => {

		if (produkt.title.toLowerCase().includes(filter.toLowerCase())) {

			var code = "";
			console.log(i);

			code += '<div class="game-container" data-product-id="' + produkt.id + '"> ';
			code += '<div class="game-image-container"><img src="';
			code += produkt.image;
			code += '" alt="' + produkt.title + '" class="game-image"></div>';
			code += '<div class="game-price">' + produkt.price + ' zł</div>';
			code += '<div class="game-title">' + produkt.title + '</div>';
			code += '<div class="game-author">Sprzedawca: ' + produkt.seller + '</div>';
			if (zapisane.includes(produkt.id)) {
				code += '<button class="add-to-cart" data-added="true" onclick="addToCart(' + produkt.id + ')">Dodano</button>';
			}
			else {
				code += '<button class="add-to-cart" data-added="false" onclick="addToCart(' + produkt.id + ')">Dodaj do koszyka</button>';
			}
			code += '</div>';
			rowcontainer.innerHTML += code;
			if (i % 3 == 2) {
				row++;
				containter.innerHTML += '<div class="row" id="row' + row + '"></div>';
				rowcontainer = document.getElementById("row" + row);
			}
			i++;
		}
	});
}

function findProductById(id) {
	var znalezionyProdukt = produkty.find(function (produkt) {
		return produkt.id === id;
	});
	return znalezionyProdukt;
}

function showItemsInCart() {
	console.log(localStorage.getItem('zapisaneId'));
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');
		if (zapisaneId) {
			zapisaneId = JSON.parse(zapisaneId);


			var containter = document.getElementById("cart-items");
			containter.innerHTML = "";
			if (zapisaneId.length == 0) {
				containter.innerHTML += '<h1 style="margin-left: 10%;">Brak produktów w koszyku</h1>';
				// tu mozna guzik wyłączyć   <- prawda ~ Mateusz
			}
			else
				zapisaneId.forEach(id => {
					var znalezionyProdukt = findProductById(id);
					var code = "";
					code += '<div class="product-tile" data-product-id="' + znalezionyProdukt.id + '">';
					code += '<img src="' + znalezionyProdukt.image + '" alt="' + znalezionyProdukt.title + '" class="product-logo">';
					code += '<div class="product-details">';
					code += '<h3>' + znalezionyProdukt.title + '</h3>';
					code += '<p>' + znalezionyProdukt.price + ' zł</p>';
					code += '</div>';
					code += '<img src="img/kosz.png" onclick="usunIdZCiasteczek(' + znalezionyProdukt.id + '); showItemsInCart()" alt="Kosz" class="cart-icon">';
					code += '</div>';
					console.log(code);
					containter.innerHTML += code;



				});

		} else {
			console.log('Brak zapisanych id w ciasteczkach.');
		}
	} else {
		console.log('Twoja przeglądarka nie obsługuje ciasteczek.');
	}

	var containter = document.getElementById("container");


}

function showProducts() {
	var containter = document.getElementById("container");
	containter.innerHTML = '<div class="row" id="row0"></div>';
	var row = 0;
	var rowcontainer = document.getElementById("row" + row);
	var i = 0;
	var zapisane = [];
	if (typeof Storage !== 'undefined') {
		var zapisaneId = localStorage.getItem('zapisaneId');
		if (zapisaneId) {
			zapisane = (JSON.parse(zapisaneId));
		}
	}
	console.log(zapisane);
	produkty.forEach(produkt => {

		var code = "";
		console.log(i);

		code += '<div class="game-container" data-product-id="' + produkt.id + '"> ';
		code += '<div class="game-image-container"><img src="';
		code += produkt.image;
		code += '" alt="' + produkt.title + '" class="game-image"></div>';
		code += '<div class="game-price">' + produkt.price + ' zł</div>';
		code += '<div class="game-title">' + produkt.title + '</div>';
		code += '<div class="game-author">Sprzedawca: ' + produkt.seller + '</div>';
		if (zapisane.includes(produkt.id)) {
			code += '<button class="add-to-cart" data-added="true" onclick="addToCart(' + produkt.id + ')">Dodano</button>';
		}
		else {
			code += '<button class="add-to-cart" data-added="false" onclick="addToCart(' + produkt.id + ')">Dodaj do koszyka</button>';
		}
		code += '</div>';
		rowcontainer.innerHTML += code;
		if (i % 3 == 2) {
			row++;
			containter.innerHTML += '<div class="row" id="row' + row + '"></div>';
			rowcontainer = document.getElementById("row" + row);
		}
		i++;
	});
}

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

	if (password !== confirmpassword) {
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
	return fetch('/api/testlogin', {
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
function fetchData(url, callback) {
	fetch(url)
		.then(response => response.json())
		.then(data => callback(data))
		.catch(error => console.error('Error:', error));
}

function fetchPost(url, data, callback) {
	if (data === null) {
		fetch(url, {
			method: 'POST'
		})
			.then(response => response.json())
			.then(data => callback(data))
			.catch(error => console.error('Error:', error));
	}
	else {
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		})
			.then(response => response.json())
			.then(data => callback(data))
			.catch(error => console.error('Error:', error));
	}
}
