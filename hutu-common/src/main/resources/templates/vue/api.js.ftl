import { get, post, put, del } from '@/utils/request'

export const ${featureName}Api = {
    queryPage(data, page, size) {
        return post(`/${entity?uncap_first}/page/<#noparse>${page}/${size}</#noparse>`, data)
    },
    add(data) {
        return post(`/${entity?uncap_first}/add`, data)
    },
    details(id) {
        return get(`/${entity?uncap_first}/details/<#noparse>${id}</#noparse>`)
    },
    update(data) {
        const id = data.id
        return put(`/${entity?uncap_first}/update/<#noparse>${id}</#noparse>`, data)
    },
    delete(id) {
        return del(`/${entity?uncap_first}/delete/<#noparse>${id}</#noparse>`)
    },
}