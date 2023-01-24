
    let user=JSON.parse(localStorage.getItem("user"))
    let token=JSON.parse(localStorage.getItem("token"))

    function fun(){

        user=JSON.parse(localStorage.getItem("user"))

        let div=document.getElementById("main");
        div.innerHTML="";
        let p1=document.createElement("p");
        let p2=document.createElement("p");
        let p3=document.createElement("p");
        p1.innerText=user.name;
        p2.innerText=user.email;
        p3.innerText=user.address;
        let btn=document.createElement("button");
        btn.innerText="Edit"

        btn.addEventListener("click",function(){
            func();
        })
        div.append(p1,p2,p3,btn)
    }

    fun();

    function func(){
        let div=document.getElementById("main");
        div.innerHTML="";
        let p1=document.createElement("input");
        let p2=document.createElement("input");
        let p3=document.createElement("input");
        p1.setAttribute("id","p1")
        p2.setAttribute("id","p2")
        p3.setAttribute("id","p3")
        p1.value=user.name;
        p2.placeholder="existing password or new password"
        p2.type="password"
        p3.value=user.address;

        let d=document.createElement("div");

        let btn=document.createElement("button");
        btn.innerText="update"

        let btn1=document.createElement("button");
        btn1.innerText="cancel"

        btn.addEventListener("click",function(){
            update();
        })

        btn1.addEventListener("click",function(){
            fun();
        })
        d.append(btn,btn1)
        div.append(p1,p3,p2,d)
    }

    async function update(){

        let pro_data={
            name:document.getElementById("p1").value,
            address:document.getElementById("p3").value,
            password:document.getElementById("p2").value,
            email:user.email
        }

        pro_data=JSON.stringify(pro_data)

        let api_link=`http://localhost:8888/students`
        let response=await fetch(api_link,{
            method:"PUT",
            body:pro_data,
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
            localStorage.setItem("user",JSON.stringify(data));
            fun();
        }).catch(function (error) {
            alert("enter password")
        });

    }
