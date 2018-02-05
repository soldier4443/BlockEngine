attribute vec3 position;
attribute vec2 textureCoords;

varying vec4 color;
varying vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
    color = vec4(0.5, 0.5, 0.5, 1.0);
    pass_textureCoords = textureCoords;
}
