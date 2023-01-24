
    function login(){
        event.preventDefault();

        let username = document.getElementById("email1").value;
        let password = document.getElementById("password1").value;
        let auth = btoa(`${username}:${password}`);

        fetch('http://localhost:8888/signIn', {
            headers: {
                'Authorization': `Basic ${auth}`
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(function (data) {
            fun(data);
        }).catch(function (error) {
            alert("wrong")
        });

    }

    function fun(data){
        if(data.role=="ROLE_ADMIN"){
            localStorage.setItem("admin",JSON.stringify(data))
            window.location.href="admin.html"
        }
        else if(data.role=="ROLE_USER"){

            localStorage.setItem("token",JSON.stringify(data.token))

            localStorage.setItem("user",JSON.stringify(data))
            
            window.location.href="user.html"
        }
        else{
            alert("wrong credentials")
        }
    }
