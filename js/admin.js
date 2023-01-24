
    document.getElementById("registerForm").addEventListener("submit", register)


    function show(){
        let div=document.getElementById("registerDiv")
        div.style.display="contents"
    }    


    let user=JSON.parse(localStorage.getItem("admin"))

    async function demo(){
        let token=user.token
        console.log(token)
        fetch(`http://localhost:8888/students`,{
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
        .then((res)=> res.json())
        .then((data)=> fun(data))

    }

    demo()

    function fun(data){
        
        let div=document.querySelector(".tbody")
        div.innerHTML=""
        data.forEach((el,i)=> {

            console.log(el)

            let div1=document.createElement("tr")
            let sr=document.createElement("td")
            let name=document.createElement("td")
            let email=document.createElement("td")
            let address=document.createElement("td")
            let btn=document.createElement("button")
            sr.innerText=i+1
            name.innerText=el.name
            email.innerText=el.email
            address.innerText=el.address
            btn.innerText="Delete"

            btn.addEventListener("click", function(){
                del(el);
            })

            if(el.email==user.email){
                let admin=document.createElement("td")
                admin.innerText=""
                div1.append(sr,name,email,address,admin)
            }
            else{
                div1.append(sr,name,email,address,btn)
            }
            
            let div=document.querySelector(".tbody")
            div.append(div1)
            
        });
    }


    async function del(data){
        let token=user.token
        let api_link=`http://localhost:8888/students/${data.email}`
        let response=await fetch(api_link,{
            method:"DELETE",
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw response;
        }).then(function (data) {
            console.log(data)
            window.location.reload();
        }).catch(function (error) {
            alert("enter password")
        });

    }

    async function register(){
        event.preventDefault();
        let data={
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            address: document.getElementById("address").value,
            password: document.getElementById("password").value,
            authorities: [
                {
                    name: "ROLE_USER"
                }
            ]
        }

        data=JSON.stringify(data)

        let api=`http://localhost:8888/students`

        let res=await fetch(api,{
            method:"POST",
            body:data,
            headers:{
                'Content-type':'application/json'
            }
        })

        let data1=await res.json()
        // console.log(data1)
        window.location.reload();

    }
