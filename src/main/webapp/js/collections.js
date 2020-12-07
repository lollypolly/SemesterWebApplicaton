document.addEventListener('DOMContentLoaded', function () {
    async function generateImage() {
        let url = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
        url = url.substring(0, url.lastIndexOf('/')) + '/cat';

        try {
            let response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.json();
            await generateImageInner(result);
        } catch (e) {
            console.log(e);
        }
    }

    function generateImageInner(result) {
        let html = '';
        for (let i = 0; i < result.length; i++) {
            html += '<div class="col-2"><a href="' + result[i].url + '" target="_blank"><img class="w-100 rounded my-3" src="' + result[i].url + '" alt="' + result[i].id + '"></a></div>';
        }
        document.getElementById("Liked").innerHTML = html;
    }

    generateImage();
}, false);
