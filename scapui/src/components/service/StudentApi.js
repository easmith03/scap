
export default {
    call(url) {
        return {
            getOne: ( id ) =>
                fetch(`${url}/${id}`, {
                    crossDomain:true,
                    method: 'GET',
                    headers: {'Content-Type':'application/json'}
                }).then(res => res.json()),

            getAll: () =>
                fetch(`${url}`, {
                    crossDomain:true,
                    method: 'GET',
                    headers: {'Content-Type':'application/json'}
                }).then(res => res.json()),

            update: (id, body) =>
                fetch(`${url}/${id}`, {
                    method: 'PATCH',
                    headers: {'Content-Type':'application/json'},
                    body: JSON.stringify(body)
                }).then(res => res.json()),

            create: (body) =>
                fetch(`${url}`, {
                method: 'POST',
                headers: {'Content-Type':'application/json'},
                body: JSON.stringify(body)
            }).then(res => res.json()),

            delete: (id) =>
                fetch(`${url}/${id}`, {
                method: 'DELETE'
            })
        }
    }
}
