in vec3 vertexPosition;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
struct Shadow
{
    // direction of light that casts shadow
    vec3 lightDirection;
    // data from camera that produces depth texture
    mat4 projectionMatrix;
    mat4 viewMatrix;
    // texture that stores depth values from shadow camera
    sampler2D depthTexture;
    // regions in shadow multiplied by (1-strength)
    float strength;
    // reduces unwanted visual artifacts
    float bias;
};
uniform bool useShadow;
uniform Shadow shadow0;
out vec3 shadowPosition0;
void main()
{
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(vertexPosition, 1.0);
    if (useShadow)
    {
        vec4 temp0 = shadow0.projectionMatrix * shadow0.viewMatrix *
        modelMatrix * vec4(vertexPosition, 1);
        shadowPosition0 = vec3( temp0 );
    }
}