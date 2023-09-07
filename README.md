# BerlinApp
BerlinApp represents a significant accomplishment as my most substantial project as of 07/2023. It serves as an app that guides users through exploring Berlin's landmarks, cool spots, cafes, bars, and more, providing concise information about each location. 

Developing the app took approximately one week, and the most challenging aspect was implementing the navigation logic. For optimal user experience, I decided to utilize BackHandler instead of NavController to ensure precise page transitions with each click. 

One of the project's key strengths is its full responsiveness, seamlessly adapting to various devices, including mobile screens, foldables, and tablets.

## Screenshots
<p align="left">
    <img src="1.png" alt="Categories Page 1" width="220" height="460">&nbsp;&nbsp;
    <img src="2.png" alt="Results Page 1" width="220" height="460">&nbsp;&nbsp;
    <img src="3.png" alt="Categories Page 2" width="220" height="460">&nbsp;&nbsp;
    <img src="4.png" alt="Results Page 2" width="220" height="460">
</p>


<style>
    .image-row {
        display: flex;
        overflow-x: auto;
        white-space: nowrap;
    }

    .image {
        flex: 0 0 auto;
        margin-right: 10px; /* Add some spacing between images */
    }

    /* Media query to switch to a column layout on small screens */
    @media (max-width: 768px) {
        .image-row {
            flex-direction: column;
            overflow-x: hidden; /* Hide horizontal scrollbar */
            white-space: normal; /* Allow text wrapping */
        }

        .image {
            margin-right: 0; /* Remove spacing between images in column layout */
            margin-bottom: 10px; /* Add some spacing between images in column layout */
        }
    }
</style>

<div class="image-row">
    <img src="1.png" alt="Categories Page 1" width="220" height="460" class="image">
    <img src="2.png" alt="Results Page 1" width="220" height="460" class="image">
    <img src="3.png" alt="Categories Page 2" width="220" height="460" class="image">
    <img src="4.png" alt="Results Page 2" width="220" height="460" class="image">
</div>
