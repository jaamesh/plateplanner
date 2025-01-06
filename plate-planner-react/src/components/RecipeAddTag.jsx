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
import recipeService from "../services/recipeService.js";
import tagService from "../services/tagService.js";


export default function App(props) {
    const [basicModal, setBasicModal] = useState(false);
    const [recipeToTag, setRecipeToTag] = useState(props.recipe);
    const [tags, setTags] = useState(null);
    const [tagsToSave, setTagsToSave] = useState(null);
    const [actionMsg, setActionMsg] = useState(null);


    const toggleOpen = () => {
        setBasicModal(!basicModal);
        loadTags();
        setActionMsg(null);
        setTagsToSave(null);
    }

    const closeWindow = () => {
        setTags(null);
        toggleOpen();
    }

    const loadTags = () => {
        if (tags == null) {
            setActionMsg("Loading Tags...");
            tagService.getAll()
                .then((response) => {
                    setTags(response.data);
                    setActionMsg(null);
                    console.log("response.data: ", response.data);
                })
                .catch((err) => {
                    setActionMsg("There was a problem connecting to the database.  Please try again later.");
                    console.log("Error: ", err.message);
                });
        }
    }

    const handleTagSelect = (e) => {
        let chosenTags = [];
        for (var i = 0; i < e.target.options.length; i++) {
            console.log("Option: ", e.target.options[i])
            if (e.target.options[i].selected)
            {
                chosenTags.push(tags[i]);
            }
        }

        setTagsToSave(chosenTags);
        console.log("chosenTags: ", chosenTags);
    }

    const updateRecipe = () => {
        setActionMsg("Updating recipe with tags...");

        for (var i = 0; i < tagsToSave.length; i++) {
            tagsToSave[i].recipes.push(recipeToTag.id)
        }
        recipeToTag.tags.push(tagsToSave);
        console.log("recipeToTag: ", recipeToTag);

        recipeService.update(recipeToTag.id, recipeToTag)
        .then((response) => {
            setActionMsg("Tags saved to recipe.");
        })
        .catch((err) => {
            setActionMsg("There was a problem connecting to the database.  Please try again later.");
            console.log("Error: ", err.message);
        });
        
    };

    return (
        <>
            <Button label="Add Tag" onClick={toggleOpen} />
            <MDBModal open={basicModal} onClose={() => setBasicModal(false)} tabIndex='-1'>
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Add tags: {recipeToTag.name}</MDBModalTitle>
                            <MDBBtn className='btn-close' color='none' onClick={toggleOpen}></MDBBtn>
                        </MDBModalHeader>

                        <MDBModalBody>
                            {actionMsg == null && tags != null &&
                                <div>
                                    <label>Select Multiple Tags</label>
                                    <div>
                                        <select name="tags" id="tags" size={tags.length} multiple onChange={e => handleTagSelect(e)}>
                                        {
                                            (() => {
                                            let container = [];
                                            let selected = false;
                                            tags.forEach((tag, index) => {
                                                for (var i = 0; i < recipeToTag.tags.length; i++) {
                                                    selected = tag.id == recipeToTag.tags[i];
                                                    if (selected) { break; }
                                                }
                                                container.push(
                                                <option key={index} value={tag.id} title={tag.description} selected={selected}>{tag.name}</option>);
                                            });
                                            return container;
                                            })()
                                        }
                                        </select>
                                    </div>
                                </div>
                            }
                            {actionMsg != null &&
                                <div>{actionMsg}</div>
                            }
                       </MDBModalBody>

                        <MDBModalFooter>
                            <Button label="Close" onClick={closeWindow} />
                            {actionMsg == null &&
                                <Button label="Tag Recipe" onClick={updateRecipe}/>
                            }      
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>
        </>
  );
}