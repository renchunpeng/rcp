/**
 * @module errors
 * @author qx
 * @date 2016/3/30
 * @function 功能说明
 */

Error.prototype.include = function(innerError) {
	this.innerError = innerError;
	return this;
};

function err(msg, num) {
	var e = new Error(msg);
	e.number = e.id = num;
	return e;
}

var errors = {

	notScanError:       err('waiting for scanning...',            10999),
	notLoginError:      err('user not login...',                  10088),
	loginFailedError:   err('server error, not login...',         10086),


	dbQueryError:       err('db query error...',                  40881),
	noSuchDataError:    err('can not find this record...',        40100),
	recordExistError:   err('the record exist...',                40104),

	readFileError:      err('read file error...',                 46002)

};

module.exports = errors;
