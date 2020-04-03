import { API_URL } from './config';

function handleError(res) {
    if (res && !res.ok) {
        throw new Error(res.statusText || "Server Error");
    }
    return res;
}

export default {
    getOne: (id) =>
        fetch(`${API_URL}/students/${id}`, {
            crossDomain:true,
            method: 'GET',
            headers: {'Content-Type':'application/json'}
        }).then(handleError)
          .then(res => res.json()),

    getAll: () =>
        fetch(`${API_URL}/students`, {
            crossDomain:true,
            method: 'GET',
            headers: {'Content-Type':'application/json'}
        }).then(handleError)
          .then(res => res.json()),

    update: (id, body) =>
        fetch(`${API_URL}/students/${id}`, {
            method: 'PATCH',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(body)
        }).then(handleError)
          .then(res => res.json()),

    create: (body) =>
        fetch(`${API_URL}/students`, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(body)
    }).then(handleError)
      .then(res => res.json()),

    delete: (id) =>
        fetch(`${API_URL}/students/${id}`, {
        method: 'DELETE'
    }).then(handleError)
}
