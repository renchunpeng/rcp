import React, { Component, PropTypes } from 'react';
import { ImagePicker } from 'antd-mobile';
import request from '../../utils/request'

const data = [{
    url: 'https://zos.alipayobjects.com/rmsportal/PZUUCKTRIHWiZSY.jpeg',
    id: '2121',
}, {
    url: 'https://zos.alipayobjects.com/rmsportal/hqQWgTXdrlmVVYi.jpeg',
    id: '2122',
}];

class ImageUpload extends React.Component {
    state = {
        files: data
    }
    submit = () => {
        let {files} = this.state;
        //获取文件对象
        let _file1 = files[2].file;
        //实例化一个表单数据对象
        var formData = new FormData();
        formData.append(_file1.name,_file1);
        console.log(formData);
        let data = request('/lflweb/springUpload/12345', {
            method: 'post',
            body: formData
        })
    }
    onChange = (files, type, index) => {
/*        console.log(files[2].file);
        //获取文件对象
        let _file = files[2].file;
        //实例化一个表单数据对象
        var formData = new FormData();
        formData.append(_file.name,_file);
        formData.append('78',_file);
        console.log(formData);
        let data = request('/lflweb/springUpload', {
            method: 'post',
            body: formData
        })
        console.log(files, type, index);*/
        this.setState({
            files,
        });
    }
    render() {
        const { files } = this.state;
        return (
            <div>
                <ImagePicker
                    files={files}
                    onChange={this.onChange}
                    onImageClick={(index, fs) => console.log(index, fs)}
                    selectable={files.length < 5}
                />


                <input type="button" onClick={this.submit} value="提交"/>
            </div>
        );
    }
}

export default ImageUpload