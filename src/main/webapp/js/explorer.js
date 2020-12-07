document.addEventListener('DOMContentLoaded', function () {

    async function generateImage() {
        let url = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
        url = url.substring(0, url.lastIndexOf('/')) + '/cat';

        try {
            for (let i = 1; i <= 5; i++) {
                // отправляем запрос на /cat
                let response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8',
                        'page': i - 1,
                        'limit': 20
                    }
                });

                let result = await response.json();
                await generateImageInner(result, i);
            }
        } catch (e) {
            console.log(e);
        }
    }


    async function generateImageInner(result, i) {
        let cat_container = document.getElementById('cat_container_' + i);

        let html = '';
        for (let i = 0; i < result.length; i++) {

            let like = await isLiked(result[i].id);

            html += '<div><a href="' + result[i].url + '" target="_blank"><img class="w-100 rounded my-3" src="' + result[i].url + '" alt="' + result[i].id + '"></a>' +
                '<img src="/images/like' + like + '.png" class="d-flex ml-auto like" alt="' + result[i].id + '" onclick="like(this)" style="width: 30px;height: 30px"></div>';
        }
        cat_container.innerHTML = html;
    }

    async function isLiked(cat_id){
        let url = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
        url = url.substring(0, url.lastIndexOf('/')) + '/like';

        try {
            for (let i = 1; i <= 5; i++) {
                let response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8',
                        'isLiked': cat_id
                    }
                });

                return await response.text();
            }
        } catch (e) {
            console.log(e);
        }
    }

    async function searchImage() {
        let url = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
        url = url.substring(0, url.lastIndexOf('/')) + '/search';

        try {
            let word = document.getElementById('search-input').value.toString();
            url += '?word=' + word;
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.json();
            await searchImageInner(result);
        } catch (e) {
            console.log(e);
        }
    }

    async function searchImageInner(result) {

        for (let i = 1; i <= 5; i++) {
            document.getElementById('cat_container_' + i).innerHTML = '';
        }
        let container_id = 1;

        for (let i = 0; i < result.length; i++) {
            let like = await isLiked(JSON.parse(result[i]).id);

            document.getElementById('cat_container_' + container_id).innerHTML += '<div><a href="' + JSON.parse(result[i]).url + '" target="_blank"><img class="w-100 rounded my-3" src="' + JSON.parse(result[i]).url + '" alt="' + JSON.parse(result[i]).id + '"></a>' +
                '<img src="/images/like' + like + '.png" class="d-flex ml-auto like" alt="' + JSON.parse(result[i]).id + '" onclick="like(this)" style="width: 30px;height: 30px"></div>';
            container_id++;
            if (container_id > 5) container_id = 1;
        }
    }


    generateImage();


    document.getElementById('search').addEventListener('click', searchImage, false);
});

async function like(item){
    let url = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
    url = url.substring(0, url.lastIndexOf('/')) + '/like';

    try {
        let response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
                'like': item.alt
            }
        });

        let result = await response.text();
        item.src = '/images/like' + result + '.png';
    } catch (e) {
        console.log(e)
    }
}



