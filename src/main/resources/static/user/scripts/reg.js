const inputProfile = window.document.getElementById('input-image');
const photo = window.document.getElementById('photo')

inputProfile.addEventListener('click', () => {
    const xhr = new XMLHttpRequest()
    const formData = new FormData();
    formData.append('photo', photo.value);
    xhr.open('POST', '/user/profile');
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson['result']) {
                    case 'SUCCESS' :
                        alert('성공')
                        break;
                    default:
                        alert('사진을 업로드하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요..');
                }
            } else {
                alert('사진을 업로드하지 못하였습니다.\n\n잠시 후 다시 시도해 주세요..');
            }
        }
    }
    xhr.send(formData);
//
});