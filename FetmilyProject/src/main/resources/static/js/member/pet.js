document.querySelector('.m').addEventListener('click', (e) => {
    if (e.target.checked) {
        document.querySelector('.w').checked = false;
    }
});

document.querySelector('.w').addEventListener('click', (e) => {
    if (e.target.checked) {
        document.querySelector('.m').checked = false;
    }
});

document.querySelector('.t').addEventListener('click', (e) => {
    if (e.target.checked) {
        document.querySelector('.f').checked = false;
    }
});

document.querySelector('.f').addEventListener('click', (e) => {
    if (e.target.checked) {
        document.querySelector('.t').checked = false;
    }
});

// 반려동물 등록
const registPet = () => {
    const petName = document.querySelector('.pet_name').value;
    const petType = document.querySelector('.pet_type').value;
    const birth = document.querySelector('.pet_birth').value;
    const weight = document.querySelector('.pet_weight').value;
    let sex;
    let neut;
    const etc = document.querySelector('.pet_etc').value;

    if (document.querySelector('.m').checked) {
        sex = '남';
    } else if (document.querySelector('.w').checked) {
        sex = '여';
    }else{
        sex = '';
    }

    if (document.querySelector('.t').checked) {
        neut = 'O';
    } else if (document.querySelector('.f').checked) {
        neut = 'X';
    }else{
        neut = '';
    }

    axios.post('/pet/insert', {
        petName: petName,
        petType: petType,
        birth: birth,
        weight: weight,
        sex: sex,
        neut: neut,
        etc: etc,
    }).then((result) => {
        if(result.data === 1){
            alert('등록되었습니다.');
        }
    }).catch((error) => {
        alert('잠시후 다시 시도해주세요.');
    })
}

async function petUpdate(){
    const petId = document.querySelector('.petId').value;
    const petName = document.querySelector('.petName').value;
    const petType = document.querySelector('.petType').value;
    const birth = document.querySelector('.birth').value;
    const weight = document.querySelector('.weight').value;
    const nuet = document.querySelector('.nuet').value;
    const sex = document.querySelector('.sex').value;
    const etc = document.querySelector('.etc').value;

    await axios.post('/pet/update', {
        memberId: memberId,
        petId: petId,
        petName: petName,
        petType: petType,
        birth: birth,
        weight: weight,
        nuet: nuet,
        sex: sex,
        etc: etc
    })
}

async function petDelete(){
    const petId = document.querySelector('.petId').value;

    await axios.delete('/pet/delete', {
        memberId: memberId,
        petId: petId
    })
}