async function signup(){
    let email = document.querySelector(".email").value;
    let name = document.querySelector(".name").value;
    let password = document.querySelector(".password").value;
    let socialNumber = document.querySelector(".socialNumber").value;
    let phone = document.querySelector(".phone").value;
    let addr = document.querySelector(".addr").value;
    let nickname = document.querySelector(".nickname").value;
    console.log(email)

    await axios.post("/member/signup", {
        email: email,
        name: name,
        password: password,
        socialNumber: socialNumber,
        phone: phone,
        addr: addr,
        nickname: nickname
    }).then((response) => {
        if(response.body === 1){
            alert("회원가입이 완료되었습니다");
        }
    }).catch((error) => {
        alert("잠시후 다시 시도해주세요");
    });
}

async function login(){
    const email = document.querySelector(".email").value;
    const password = document.querySelector(".password").value;

     await axios.post("/member/login", {
       email: email,
       password: password
    }).then(response => {
         if(response.data.memberId !== null){
             localStorage.setItem('memberId', response.data.memberId);
             localStorage.setItem('nickname', response.data.nickname);
         }else{
             alert('이메일 또는 패스워드가 일치하지 않습니다.')
         }
     });
}

async function memberDelete(){
    const memberId = localStorage.getItem('memberId');

    await axios.delete("/member/delete", {
        memberId: memberId,
    }).then((result) => {
        
    }).catch((error) => {});
}

async function memberUpdate(){
    const memberId = localStorage.getItem('memberId');
    const name = document.querySelector(".name").value;
    const password = document.querySelector(".password").value;
    const phone = document.querySelector(".phone").value;
    const addr = document.querySelector(".addr").value;
    const nickName = document.querySelector(".nickName").value;

    await axios.post("/member/update", {
        memberId: memberId,
        name: name,
        password: password,
        phone: phone,
        addr: addr,
        nickName: nickName
    }).then((result) => {})
        .catch((error) => {});
}

async function memberInfo(){
    const memberId = localStorage.getItem('memberId');
    await axios.get(`/member?memberId=${memberId}`)
        .then((result) => {
            return result.data;
        })
}