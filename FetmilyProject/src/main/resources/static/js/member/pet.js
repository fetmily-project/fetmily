// 반려동물 등록
const registPet = () => {
    const petName = document.querySelector('.pet_name').value;
    const petType = document.querySelector('.pet_type').value;
    const birth = document.querySelector('.pet_birth').value;
    const weight = document.querySelector('.pet_weight').value;
    let petImage;
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

    const formObj = new FormData();

    const fileInput = document.querySelector("input[name='files']")

    console.log(fileInput.files)

    const files = fileInput.files

    for (let i = 0; i < files.length; i++) {
        formObj.append("files", files[i]);
    }

    uploadToServer(formObj).then(result => {
        petImage = `${result[0].uuid}_${result[0].fileName}`;

        axios.post('/pet/insert', {
            petName: petName,
            petType: petType,
            birth: birth,
            weight: weight,
            sex: sex,
            neut: neut,
            etc: etc,
            petImage: petImage
        }).then((result) => {
            if(result.data === 1){
                alert('등록되었습니다.');
                // window.location.href="/pet/list";
            }
        }).catch((error) => {
            alert('잠시후 다시 시도해주세요.');
        })
    })
}

async function petUpdate(pet_id){
    const petId = pet_id;
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

    const formObj = new FormData();

    const fileInput = document.querySelector("input[name='files']")

    console.log(fileInput.files)

    const files = fileInput.files

    for (let i = 0; i < files.length; i++) {
        formObj.append("files", files[i]);
    }

    uploadToServer(formObj).then(result => {
        petImage = `${result[0].uuid}_${result[0].fileName}`;

        axios.put('/pet/update', {
            petId: petId,
            petName: petName,
            petType: petType,
            birth: birth,
            weight: weight,
            sex: sex,
            neut: neut,
            etc: etc,
            petImage: petImage
        }).then((result) => {
            if(result.data === 1){
                alert('등록되었습니다.');
                window.location.href="/pet/list";
            }
        }).catch((error) => {
            alert('잠시후 다시 시도해주세요.');
        })
    })
}

async function petDelete(petId){
    await axios.delete(`/pet/delete?petId=${petId}`)
        .then((result) => {
            if(result.data){
                location.reload(true);
            }
        }).catch((error) => {
            alert('잠시후 다시 시도해주세요.');
        })
}