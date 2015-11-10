package model;

/**
 * Document to be stored in support MongoDB
 *
 * @author Sebastian Gamba Pinilla
 */
public class MongoDocument {

	private ActionEnum action;
	private String content;

	public MongoDocument(){

	}

	public MongoDocument(String action, String content){
		this.content = content;
		try {
			this.action = ActionEnum.valueOf(action);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public ActionEnum getAction() {
		return action;
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MongoDocument [action=" + action + ", content=" + content + "]";
	}
}
