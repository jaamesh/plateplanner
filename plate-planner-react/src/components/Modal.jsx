import Button from "./Button";

function Modal({ title = "Modal Title", modalText = "Modal Text Here" }) {

    <div className="modal fade" id="modal" tabIndex="-1" role="dialog" aria-labelledby="Modal" aria-hidden="true">
        <div className="modal-dialog" role="document">
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="modalLabel">{title}</h5>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                {modalText}
                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                    <Button label="Save Changes" onClick={() => console.log("Changes Saved")} />
                </div>
            </div>
        </div>
    </div>

}

export default Modal;