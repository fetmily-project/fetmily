async function get1(boardId) {

    const result = await axios.get(`/replies/list/${boardId}`)

    //console.log(result)

    return result;
}


// async function getList({bno, page, size, goLast}){
//
//     const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})
//
//     return result.data
// }


async function getList({boardId, page, size, goLast}){

    const result = await axios.get(`/replies/list/${boardId}`, {params: {page, size}})

    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({boardId:boardId, page:lastPage, size:size})

    }

    return result.data
}


async function addReply(replyObj) {
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}

async function getReply(replyId) {
    const response = await axios.get(`/replies/${replyId}`)
    return response.data
}

async function modifyReply(replyObj) {

    const response = await axios.put(`/replies/${replyObj.replyId}`, replyObj)
    return response.data
}

async function removeReply(replyId) {
    const response = await axios.delete(`/replies/${replyId}`)
    return response.data
}