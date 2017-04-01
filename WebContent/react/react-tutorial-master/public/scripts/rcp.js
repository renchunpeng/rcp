// tutorial5.js
var CommentList = React.createClass({
  render: function() {
    return (
      <div className="commentList">
        <Comment author="Pete Hunt">This is one comment</Comment>
        <Comment author="Jordan Walke">This is *another* comment</Comment>
      </div>
    );
  }
});


// tutorial4.js
var Comment = React.createClass({
  rawMarkup: function() {
	var md = new Remarkable();
	var rawMarkup = md.render(this.props.children.toString());
	return { __html: rawMarkup };
	},

  render: function() {
    return (
      <div className="comment">
        <h2 className="commentAuthor">
          {this.props.author}
        </h2>
        <span dangerouslySetInnerHTML={this.rawMarkup()} />
      </div>
    );
  }
});


ReactDOM.render(
  <CommentList />,
  document.getElementById('content')
);