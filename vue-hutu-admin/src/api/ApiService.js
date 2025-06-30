import request from '@/utils/request';

class ApiService {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
  }

  page (param) { 
    return request({
      url: `${this.baseUrl}/page/${param.page}/${param.size}`,
      method: 'post',
      data: param,
    });
  }

  deleteById(id) {
    return request({
      url: `${this.baseUrl}/delete/${id}`,
      method: 'delete',
    });
  }

  save(data) {
    return request({
      url: `${this.baseUrl}/save`,
      method: 'post',
      data: data,
    });
  }

  update(data) {
    return request({
      url: `${this.baseUrl}/update`,
      method: 'put',
      data: data,
    });
  }

  getById(id) {
    return request({
      url: `${this.baseUrl}/getById/${id}`,
      method: 'get',
    });
  }
}

export default ApiService;