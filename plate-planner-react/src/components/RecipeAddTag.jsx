import React, { useState } from 'react';
import {
  MDBBtn,
  MDBModal,
  MDBModalDialog,
  MDBModalContent,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
  MDBModalFooter,
} from 'mdb-react-ui-kit';
import Button from "./Button";


export default function App(props) {
    const [basicModal, setBasicModal] = useState(false);
    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(null);
    const [tagSaved, setTagSaved] = useState(false);

    const toggleOpen = () => {
        setBasicModal(!basicModal);
        // TODO: If tagSaved is true, refresh the recipe to show the added tags.
        setTagSaved(false);
    }

    const tagRecipe = () => {
        setTagSaved(true);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        fetch('http://localhost:8080/search-recipes?q=' + query)
        .then(response => response.json())
        .then(data => { 
            setSearchRecipes(data);
            setLoading(false);
        })
        .catch(error => {
            setError(err.message);
            setLoading(false);
        });
    };

    if (loading) {
        return <p>Loading recipes...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <>
            <Button label="Add Tag" onClick={toggleOpen} />
            <MDBModal open={basicModal} onClose={() => setBasicModal(false)} tabIndex='-1'>
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Modal title</MDBModalTitle>
                            <MDBBtn className='btn-close' color='none' onClick={toggleOpen}></MDBBtn>
                        </MDBModalHeader>

                        <MDBModalBody>
                            {!tagSaved &&
                                <div>
                                    Add tag to recipe ID: {props.recipeId}<br/>
                                    <label>
                                        Tag to Add:
                                    </label><br/>
                                    Now<br/>
                                    is<br/>
                                    the<br/>
                                    time<br/>
                                    for<br/>
                                    all<br/>
                                    good<br/>
                                </div>
                            }
                            {tagSaved &&
                                <div>Tag saved successfully!</div>
                            }
                       </MDBModalBody>

                        <MDBModalFooter>
                            <Button label="Close" onClick={toggleOpen} />
                            {!tagSaved &&
                                <Button label="Tag Recipe" onClick={tagRecipe}/>
                            }      
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>
        </>
  );
}