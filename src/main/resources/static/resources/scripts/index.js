const inputPostIndex = window.document.getElementById('postIndexInput');
const postIndex = window.document.getElementById('postIndex')
const comment = window.document.getElementById('comment')

const formData = new FormData();
formData.append('postIndex', postIndex.value);

inputPostIndex.addEventListener('click', () => {
    const xhr = new XMLHttpRequest()
    const formData = new FormData();
    formData.append('comment', comment.value);
    xhr.open('POST', '');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'SUCCESS' :
                        break;
                    default:
                        alert('댓글 입력에 실패하였습니다. 다시 시도해주세요.');
                }
            } else {
                alert('댓글 입력에 실패하였습니다. 다시 시도해주세요.');
            }
        }
    }
    xhr.send(formData);
//
});