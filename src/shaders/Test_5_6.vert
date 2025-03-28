uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
in vec3 VertexPosition;
in vec2 vertexUV;
out vec2 UV;
void main()
{
    vec4 pos = vec4(VertexPosition, 1.0);
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * pos;
    UV = vertexUV;
}