async function signup(){
    let email = document.querySelector(".email").value;
    let password = document.querySelector(".password").value;
    let nickname = document.querySelector(".nickname").value;
    let password_check = document.querySelector(".password_check").value;

    await axios.post("/member/signup", {
        email: email,
        password: password,
        nickname: nickname
    }).then((response) => {
        if(response.data === 1){
            alert("회원가입이 완료되었습니다");
            document.querySelector('.modal').style.display = 'none';
            document.querySelector(".email").value = '';
            document.querySelector(".password").value = '';
            document.querySelector(".nickname").value = '';
            document.querySelector(".password_check").value = '';
            document.querySelector('.check1').childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            document.querySelector('.check2').childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            document.querySelector('.check3').childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            document.querySelector('.all_check').childNodes[0].attributes[0].nodeValue = '/assets/member/bigCheck.svg';
        }else if(response.data === 2){
            alert("이미 가입된 이메일입니다");
        }else if(response.data === 3){
            alert("이미 존재하는 닉네임입니다");
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
         if(response.data.memberId !== null) {
             localStorage.setItem('memberId', response.data.memberId);
             localStorage.setItem('nickname', response.data.nickname);
         }
     }).catch((error) => {
         alert('이메일 또는 패스워드가 일치하지 않습니다.')
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
    await  axios.get(`/member?memberId=${memberId}`)
        .then((result) => {
            return result.data;
        })
}



document.addEventListener('DOMContentLoaded', () => {
    const check1= document.querySelector('.check1');
    const check2= document.querySelector('.check2');
    const check3= document.querySelector('.check3');
    const allCheck = document.querySelector('.all_check');
    let checkCnt = 0;

    const allCheckTrue = () => {
        if(checkCnt === 3){
            allCheck.childNodes[0].attributes[0].nodeValue = '/assets/member/big_check_true.svg';
        }else{
            allCheck.childNodes[0].attributes[0].nodeValue = '/assets/member/bigCheck.svg';
        }
    }

    check1.addEventListener('click', (e) => {
        console.log(check1.childNodes[0].attributes[0].nodeValue)

        if(check1.childNodes[0].attributes[0].nodeValue === '/assets/member/smallCheck.svg'){
            check1.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            checkCnt++;
        }else{
            check1.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            checkCnt--;
        }
        allCheckTrue();
    });

    check2.addEventListener('click', (e) => {
        if(check2.childNodes[0].attributes[0].nodeValue === '/assets/member/smallCheck.svg'){
            check2.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            checkCnt++;
        }else{
            check2.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            checkCnt--;
        }
        allCheckTrue();
    });

    check3.addEventListener('click', (e) => {
        if(check3.childNodes[0].attributes[0].nodeValue === '/assets/member/smallCheck.svg'){
            check3.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            checkCnt++;
        }else{
            check3.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            checkCnt--;
        }
        allCheckTrue();
    });

    allCheck.addEventListener('click', (e) => {
        if(allCheck.childNodes[0].attributes[0].nodeValue === '/assets/member/bigCheck.svg'){
            allCheck.childNodes[0].attributes[0].nodeValue = '/assets/member/big_check_true.svg';
            check1.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            check2.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            check3.childNodes[0].attributes[0].nodeValue = '/assets/member/small_check_true.svg';
            checkCnt = 3;
        }else{
            allCheck.childNodes[0].attributes[0].nodeValue = '/assets/member/bigCheck.svg';
            check1.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            check2.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            check3.childNodes[0].attributes[0].nodeValue = '/assets/member/smallCheck.svg';
            checkCnt = 0;
        }
        allCheckTrue();
    });

    document.querySelector('.signup_btn').addEventListener('click', (e) => {
        if(checkCnt === 3){
            signup();
        }else{
            alert('이용약관을 체크해주세요.');
        }

    });

    document.querySelector('.login_btn').addEventListener('click', () => {
        login();
    });

    const modal = document.querySelector('.modal');
    const btnOpenModal=document.querySelector('.btn-open-modal');

    btnOpenModal.addEventListener("click", ()=>{
        modal.style.display="flex";
    });

    document.querySelector('.modal_delete').addEventListener('click', () => {
        modal.style.display="none";
    });
});
