async function petInfo(){
    const memberId = localStorage.getItem('memberId');

    const petInfo = await axios.get(`/pet/memberId=${memberId}`);

    return petInfo.data;
}

async function petInsert(){
    const memberId = localStorage.getItem('memberId');
    const petName = document.querySelector('.petName').value;
    const petType = document.querySelector('.petType').value;
    const birth = document.querySelector('.birth').value;
    const weight = document.querySelector('.weight').value;
    const nuet = document.querySelector('.nuet').value;
    const sex = document.querySelector('.sex').value;
    const etc = document.querySelector('.etc').value;

    await axios.post('/pet/insert', {
        memberId: memberId,
        petName: petName,
        petType: petType,
        birth: birth,
        weight: weight,
        nuet: nuet,
        sex: sex,
        etc: etc
    })
}

async function petUpdate(){
    const memberId = localStorage.getItem('memberId');
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
    const memberId = localStorage.getItem('memberId');
    const petId = document.querySelector('.petId').value;

    await axios.delete('/pet/delete', {
        memberId: memberId,
        petId: petId
    })
}